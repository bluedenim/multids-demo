package com.van;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Primary;

/**
 * Created by vly on 1/23/2016.
 */
@Configuration
/**
 * Configures the data sources we will be working with. The actual configuration parameters are
 * delegated to the environment (e.g. resources/application.properties).
 */
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.ds1")
    /**
     * Data source one (also the primary/default). By default, the name of the method ("dataSourceOne") will be used for
     * the datasource name. Or we can use @Bean(name="...").
     */
    public DataSource dataSourceOne() {
        return new DataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.ds2")
    /**
     * Data source two. By default, the name of the method ("dataSourceTwo") will be used for
     * the datasource name. Or we can use @Bean(name="...").
     */
    public DataSource dataSourceTwo() {
        return new DataSource();
    }
}
