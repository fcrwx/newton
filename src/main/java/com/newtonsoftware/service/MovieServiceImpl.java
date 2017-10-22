package com.newtonsoftware.service;

import com.newtonsoftware.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Query implementation for The Open Movie Database (http://www.omdbapi.com/).
 * <p>
 * Only searches movie titles (no series or episodes). Currently hard-coded to search
 * for movies containing the word 'newton' but could be easily modified to accept
 * a query parameter.
 * <p>
 * Note: This performs multiple GETs against the OMDB until all results have been found.
 * This obviously has performance/scale implications for more common words in movie
 * titles, so this would need to be modified to work with pagination controls in the UI.
 * <p>
 * Endpoints:
 * /api/search/title - Finds all movies with titles containing 'newton'
 *
 * @author Karl Olson (karl.olson@gmail.com)
 */

@Path("/search")
public class MovieServiceImpl implements MovieService {

  private final static Logger LOGGER = Logger.getLogger(MovieServiceImpl.class.getName());
  private final static int resultsPerPage = 10; // OMDB restriction, non-configurable

  @GET
  @Path("/title")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> searchTitles() {

    int pageNumber = 1;
    boolean keepGoing = true;

    List<Movie> movies = new ArrayList<>();

    while (keepGoing) {
      String json = queryOMDB(pageNumber);
      LOGGER.info("json: " + json);

      JSONObject obj = new JSONObject(json);
      int totalResults = obj.getInt("totalResults");
      JSONArray searchResults = obj.getJSONArray("Search");

      for (int i = 0; i < searchResults.length(); i++) {
        String title = searchResults.getJSONObject(i).getString("Title");
        String year = searchResults.getJSONObject(i).getString("Year");
        String imdbID = searchResults.getJSONObject(i).getString("imdbID");
        movies.add(new Movie(title, year, "http://www.imdb.com/title/" + imdbID));
      }

      pageNumber++;

      if (pageNumber * resultsPerPage >= totalResults) {
        keepGoing = false;
      }
    }

    return movies;
  }

  private String queryOMDB(int pageNumber) {
    String baseUrl = "http://www.omdbapi.com/";
    String searchString = "newton";
    // String apiKey = "edeb260"; // Newton-provided key is invalid
    String apiKey = "cec31c41"; // belongs to me
    String type = "movie"; // exclude series and episodes

    StringBuilder urlString = new StringBuilder()
      .append(baseUrl)
      .append("/?s=")
      .append(searchString)
      .append("&apikey=")
      .append(apiKey)
      .append("&type=")
      .append(type)
      .append("&page=")
      .append(pageNumber);

    LOGGER.info("url: " + urlString);

    String json = null;
    try {
      URL url = new URL(urlString.toString());
      URLConnection conn = url.openConnection();
      InputStream is = conn.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line;
      StringBuilder sb = new StringBuilder();
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      json = sb.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return json;
  }

}
