package com.control.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
* https://howtodoinjava.com/spring-boot2/datasource-configuration/
*
*/
@Configuration
public class JpaConfig {

    @Bean(name = "control")
    @Primary
    public DataSource control()
    {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=control;encrypt=true;trustServerCertificate=true");
        dataSourceBuilder.username("master");
        dataSourceBuilder.password("mestre");
        return dataSourceBuilder.build();
    }

    @Bean(name = "control_log")
    public DataSource control_log()
    {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=control_log;encrypt=true;trustServerCertificate=true");
        dataSourceBuilder.username("master");
        dataSourceBuilder.password("mestre");
        return dataSourceBuilder.build();
    }
}
