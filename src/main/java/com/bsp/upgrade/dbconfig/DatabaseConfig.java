package com.bsp.upgrade.dbconfig;
import com.bsp.upgrade.repository.EntityRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
//import oracle.jdbc.datasource.OracleDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bsp.upgrade.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)

public class DatabaseConfig {



    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "devDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource devDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "stageDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.stage11")
    public DataSource stageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "prodDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.prod11")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "devRepository")
    public EntityRepo devRepository(@Qualifier("devDataSource") DataSource devDataSource,
                                    EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JpaRepositoryFactory(entityManager).getRepository(EntityRepo.class);
    }

    @Primary
    @Bean(name = "stageRepository")
    public EntityRepo stageRepository(@Qualifier("stageDataSource") DataSource stageDataSource,
                                      EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JpaRepositoryFactory(entityManager).getRepository(EntityRepo.class);
    }

    @Primary
    @Bean(name = "prodRepository")
    public EntityRepo prodRepository(@Qualifier("prodDataSource") DataSource prodDataSource,
                                     EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JpaRepositoryFactory(entityManager).getRepository(EntityRepo.class);
    }

    @Bean

    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
             EntityManagerFactoryBuilder builder,
            @Qualifier("devDataSource") DataSource devDataSource ,
            @Qualifier("stageDataSource") DataSource stageDataSource,
            @Qualifier("prodDataSource") DataSource prodDataSource) {

        return builder
                .dataSource(devDataSource)
                .packages("com.bsp.upgrade.entity")
                .persistenceUnit("EsbServiceInf")
                .build();
    }



    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
