// Service to perform a GET and find all movie titles containing 'newton'.
// Injects Angular's $q to return a promise for the asynchronous call.
//
// @author  Karl Olson (karl.olson@gmail.com)


app.service('newtonFlixTitlesService', function ($q, $http, NEWTON_TITLE_URL) {

  this.getTitles = function () {

    var defer = $q.defer();

    $http({
      method: 'GET',
      url: NEWTON_TITLE_URL
    }).then(function successCallback(response) {
      defer.resolve(response);
    }, function errorCallback() {
      console.log("Failed to retrieve movies list.");
      defer.reject([]);
    });

    return defer.promise;
  }

});
