package com.newtonsoftware.model;

/**
 * Model for a basic 'Movie' object.
 *
 * @author  Karl Olson (karl.olson@gmail.com)
 */

public class Movie {

  private String title;
  private String year;
  private String link;

  public Movie(String title, String year, String link) {
    this.title = title;
    this.year = year;
    this.link = link;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public String toString() {
    return title + "::" + year + "::" + link;
  }

}
