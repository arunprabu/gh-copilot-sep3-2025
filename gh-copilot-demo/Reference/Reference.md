go to terminal and try dep installations
  ./mvnw clean install

  it will also compile source files, compile test files and build. 

  once build succesful, target folder will have .jar 



then, run 
  java -jar target/products-rest-api-0.0.1-SNAPSHOT.jar



===
Directory structure
----
  src/main/java/com/hexaware/labs/controller
  src/main/java/com/hexaware/labs/service
  src/main/java/com/hexaware/labs/repo (database connections)
  src/main/java/com/hexaware/labs/model (schema) 



agent mode prompt to execute
===
    1. create products rest api with contoller, service, repo and model layers for h2 db.
    it should handle CRUD operations.
    make sure you follow the right project structure like the following
    src/main/java/com/hexaware/labs/controller
    src/main/java/com/hexaware/labs/service
    src/main/java/com/hexaware/labs/repo
    src/main/java/com/hexaware/labs/model

    ensure you follow standard recommended conventions, best practices.

    2. run the app

    3. generate swagger documentation for the apis

    4. give me swagger docs url

    5. give me h2 db access url from browser (localhost:8080/h2-console) and h2 datasource jdbc url to connect to from browser. (we can get this from application.properties or yaml)


Now, in another agent mode chat...
    1. run test cases
    2. generate tests for api - controller, service, repo
    3. run the test cases 
    4. generate code coverage reports 




