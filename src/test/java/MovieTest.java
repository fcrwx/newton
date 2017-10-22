import com.newtonsoftware.model.Movie;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {

  @Test
  public void newMovieParametersShouldMatch() {
    String title = "a";
    String year = "0";
    String link = "example.com";
    Movie movie = new Movie(title, year, link);

    // assert statements
    assertEquals("movie title must match", title, movie.getTitle());
    assertEquals("movie year must match", year, movie.getYear());
    assertEquals("movie link must match", link, movie.getLink());
  }

  @Test
  public void movieTitleSetterShouldMatch() {
    String title = "c";
    String newTitle = "d";
    Movie movie = new Movie(title, null, null);
    movie.setTitle(newTitle);

    // assert statements
    assertEquals("movie title is set correctly", newTitle, movie.getTitle());
  }

  @Test
  public void movieYearSetterShouldMatch() {
    String year = "5";
    String newYear = "6";
    Movie movie = new Movie(null, year, null);
    movie.setYear(newYear);

    // assert statements
    assertEquals("movie year is set correctly", newYear, movie.getYear());
  }

  @Test
  public void movieLinkSetterShouldMatch() {
    String link = "g";
    String newLink = "h";
    Movie movie = new Movie(null, null, link);
    movie.setLink(newLink);

    // assert statements
    assertEquals("movie link is set correctly", newLink, movie.getLink());
  }
}

