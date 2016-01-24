# multids-demo

## A sample Spring Boot app showing how to configure one to use multiple DataSources.

Probably one of the more common questions people (myself included) have about Spring Boot. Sooner or later you 
will run into a situation when you need to work with two databases.

The Spring Boot documentation is concise, perhaps as a way to illustrate how _easy_ it is to work with it. However,
it is surprising how little documentation there is on working with multiple DataSources.

This sample all shows how it can be done.

The main points, if looking through the app is too much for you:

* Add a Configuration class (anywhere in the tree where it can be scanned by Spring Boot, typically a _sibling_ of your
    `@SpringBootApplication` class (`com.van.MultiDatasourceDemoApplication` in this app).
* In that Configuration class (`com.van.DataSourceConfiguration` in this app):
    * Declare a @Bean method to return a `DataSource` for each data source.
    * If you want to configure the data sources via the environment (e.g. `application.properties` and the like), 
      add `@ConfigurationProperties(prefix="...")` with the prefix for your properties.
    * One of the data sources need to be annotated with `@Primary` for it to be the _default_ data source (or you may
      encounter errors during start-up).
    * The method can return any implementation of `DataSource`, though I use `org.apache.tomcat.jdbc.pool.DataSource`
      so that all the connection pool parameters are also available.
* Configure the data sources in `application.properties` (or other environmental means)
* When using the datasources, `Autowire` them in as usual (see `com.van.services.DataAccessService`). 
    * Without further qualifiers, the default datasource is used just as before.
    * With a qualifier (such as `@Resource(name="...")`), you can select the data source you want.
    
That's pretty much it. The rest of the app is basically some boilerplate stuff so that you can run it
    (`mvn spring-boot:run`) and see it in action with http://localhost:8080/.
    
# Building & Running

You will need:

* Java 1.8
* Maven (3.2.2 tested)

Simply clone the repo and run with Maven:

```
mvn spring-boot:run
```
