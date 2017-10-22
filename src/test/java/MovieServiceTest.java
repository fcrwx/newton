import com.newtonsoftware.model.Movie;
import com.newtonsoftware.service.MovieServiceImpl;
import org.junit.Test;

import java.util.List;

public class MovieServiceTest {

  @Test
  public void newMovieParametersShouldMatch() {

    MovieServiceImpl service = new MovieServiceImpl();
    List<Movie> movies = service.searchTitles();

    // assert statements
    assert (movies.size() > 0);
  }

}
