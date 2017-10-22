// Controller used to get all movies with a title containing 'newton'.
// Uses newtonFlixTitlesService to make the REST call to the backend.
//
// @author  Karl Olson (karl.olson@gmail.com)


app.controller('newtonFlixController', function ($scope, newtonFlixTitlesService) {

  newtonFlixTitlesService.getTitles()
    .then(
      function (movies) {
        $scope.movies = movies.data;
      }
    )

});
