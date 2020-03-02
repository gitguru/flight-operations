# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.example.airport.flight-operations' is invalid and this project uses 'com.example.airport.flightoperations' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:
* [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)

## Additional helpful links
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Flights database 
* [OpenFlights data](https://openflights.org/data.html)

# Flight Operations tech stack
This is sample code that can be used as a boilerplate for several other projects manipulating data in a database. It uses a simple database H2 Database. 

## Frameworks and libraries used
* Spring Boot v2.2.4.RELEASE
* Spring Boot Data (JPA) v2.2.4.RELEASE
* Hibernate v5.4.x 
* H2 Database Engine v1.4.x
* Thymeleaf in conjunction with Spring MVC for the view layer v2.2.4.RELEASE
* OpenCSV as an easy-to-use CSV parser library v5.0 
* FasterXML/Jackson-Core as XML/JSON data binding for XML/JSON processing v2.9.10
* Bootstrap CSS Framework v4.0.0
* Font Awesome Free icon set v5.12.1
* Gradle as dependency management and build system v6.2.1
* Java SDK v1.8.x

## How to run the application from command line using a `*nix` or `OS X` system
1. Make sure to have Java SDK 1.8.x
2. Make sure you have write permissions to `/tmp` folder since a database file will be stored in that location with `/tmp/testdb` name
3. Pull project from git
4. From command line run
    * `$ ./gradlew check`
    * `$ ./gradlew bootJar`
5. That will compile application and create a runnable Jar file
6. Run the application
    * `$ java -jar build/libs/flight-operations-0.0.1-SNAPSHOT.jar`       
7. In you HTTP browser navigate to `http://localhost:8080/` the application must return and show index page

## Pre-loaded data
By default the application comes with pre-loaded Airports and Airlines data which you can fine in project source under `src/main/resources/data` folder.

## Sample flights operations feed data
Sample feed CSV/XML/JSON data and structures are provided also under `src/main/resources/sample-feed-data` folder. There you can find
* `flights_feed.csv` - Sample CSV feed data
* `flights_feed.json` - Sample JSON feed data
* `flights_feed.xml` - Sample XML feed data

## Querying the database
If you want to query H2 Database point your browser to the following address 
* `http://localhost:8080/h2-console`
*  Use the following settings to connect if not provided by default
    * JDBC URL: `jdbc:h2:/tmp/testdb`
    * User name: `sa`
    * Password: `password`  

## Using the application
Few options as sample navigation and file upload are provided. 
1. Arrivals/Departures - User is able to browse and find all arrivals to or departures from a selected airport
2. Airports - User is able to browse a list of current system airports
3. Airlines - User is able to browse a list of current system airlines
4. Feed - User is able to feed the database flight operations. It accepts CSV, XML and JSON file formats

### Sample data structures
#### CSV
```
Number,DepartureTime,ArrivalTime,Origin,Destination,Airline,Gate,Status
IBE - 844,2020-02-27T20:00,2020-02-27T22:00,BAI,UPL,KUH,111,ON_TIME
AVG - 1111,2020-02-27T08:35,2020-02-27T11:45,LIR,SJO,ARU,9,ON_TIME
LAX - 91,2020-02-27T10:49,2020-02-27T13:05,LIR,SJO,BOV,17,DELAYED
```
#### XML
```
<?xml version='1.0' encoding='UTF-8'?>
<FlightOperations>
	<FlightOperation>
		<Number>AX-223</Number>
		<DepartureTime>2020-02-29T07:11</DepartureTime>
		<ArrivalTime>2020-02-29T08:39</ArrivalTime>
		<Origin>SJO</Origin>
		<Destination>LIR</Destination>
		<Airline>BOV</Airline>
		<Gate>3</Gate>
		<Status>ON_TIME</Status>
	</FlightOperation>
	<FlightOperation>
		<Number>IB-777</Number>
		<DepartureTime>2020-02-29T07:11</DepartureTime>
		<ArrivalTime>2020-02-29T14:30</ArrivalTime>
		<Origin>LSL</Origin>
		<Destination>DRK</Destination>
		<Airline>CSX</Airline>
		<Gate>1</Gate>
		<Status>DELAYED</Status>
	</FlightOperation>
</FlightOperations>
```
#### JSON
```
{
  "FlightOperations": [
    {
        "Number": "MM-233",
        "DepartureTime": "2020-03-01T12:00",
        "ArrivalTime": "2020-03-01T17:33",
        "Origin": "SYQ",
        "Destination": "BCL",
        "Airline": "IDX",
        "Gate": "66",
        "Status": "ON_TIME"
    },
    {
        "Number": "MM-234",
        "DepartureTime": "2020-03-01T18:50",
        "ArrivalTime": "2020-03-01T20:00",
        "Origin": "BCL",
        "Destination": "SYQ",
        "Airline": "IDX",
        "Gate": "67",
        "Status": "ON_TIME"
    }
  ]
}
```

## TODO
Since this is just a sample boilerplate code with basic functionality there are still things to improve like
1. Add security so only authorized users can feed the database
2. Add better file validation since currently only file type is validated
3. Improve error handling when feed data is not able to update database 
    * Invalid airline code
    * Invalid airport code
    * Malformed data (dates)
4. Improve code re-utilization 
    * `FlightOperationsCSVRequest, FlightOperationsJSONRequest, FlightOperationsXMLRequest` can be unified using an interface to `FlightOperationServiceImpl` service just need to deal with one save operation.
5. Add unit test
    * Test services methods to check correct operation
    * Specific tests to flight operations data feed
6. Add RestFUL operations to other systems can be integrated with core functionality of flights operations
