package com.bsp.upgrade.dbconfig;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bsp.upgrade.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dev11DataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.stage11")
//    public DataSource stage11DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.prod11")
//    public DataSource prod11DataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dev11DataSource") DataSource dev11DataSource
//            @Qualifier("stage11DataSource") DataSource stage11DataSource,
//            @Qualifier("prod11DataSource") DataSource prod11DataSource
    ) {
        Map<String, DataSource> dataSources = new HashMap<>();
        dataSources.put("dev11", dev11DataSource);
//        dataSources.put("stage11", stage11DataSource);
//        dataSources.put("prod11", prod11DataSource);

        return builder
                .dataSource(dev11DataSource) // Set a default data source
                .packages("com.bsp.upgrade.entity")
                .persistenceUnit("EsbServiceInf")
                .properties(dataSources)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
