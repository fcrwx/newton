# newton flix

Uses the Open Movie Database API to search for all movies containing the word 'newton' in the title.

After running ```mvn install```, deploy the .war to an Apache Tomcat container.

The front end is written in Angular. It makes a REST call to the backend to get the list of movies. The backend is responsible for making a GET request to the Open Movie Database.
