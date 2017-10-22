package com.newtonsoftware.service;

import com.newtonsoftware.model.Movie;

import java.util.List;

/**
 * Interface for the implementation of the MovieService which is used to
 * query The Open Movie Database (http://www.omdbapi.com/).
 *
 * @author  Karl Olson (karl.olson@gmail.com)
 */

public interface MovieService {

  List<Movie> searchTitles();

}
