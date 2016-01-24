package com.van;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Simple demo/example of working with multiple connection pooled datasources in a Spring Boot app.
 * <br/>
 * The configuration class {@link DataSourceConfiguration} is picked up to configure the datasources. They
 * in turn delegate the configuration to the environment (including properties files).
 * <br/>
 * Finally, the {@link com.van.controllers.Main} controller uses the the {@link com.van.services.DataAccessService}
 * service which autowires in the {@link javax.sql.DataSource} classes to work with.
 */
public class MultiDatasourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourceDemoApplication.class, args);
	}

}
