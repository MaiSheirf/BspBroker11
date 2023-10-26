package com.bsp.upgrade.dbconfig;
import com.bsp.upgrade.repository.EntityRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
//import oracle.jdbc.datasource.OracleDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.core.env.Environment;

import java.util.HashMap;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bsp.upgrade.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class DatabaseConfig {


    @Value("${spring.datasource.url}")
    private String devurl = "jdbc:oracle:thin:@10.19.137.5:1521:ibblog";

    @Value("${spring.datasource.url}")
    private String stageurl = "jdbc:oracle:thin:@localhost:1521:iibdev";

    @Value("${spring.datasource.url}")
    private String produrl = "jdbc:oracle:thin:@localhost:1522:rmbprod";

    @Value("${spring.datasource.username}")
    private String devusername = "iibup";

    @Value("${spring.datasource.username}")
    private String stageusername = "iibqc";

    @Value("${spring.datasource.username}")
    private String produsername ="BSPREMEDYPROD";

    @Value("${spring.datasource.password}")
    private String devpassword = "iibup";

    @Value("${spring.datasource.password}")
    private String stagepassword = "iibqc";

    @Value("${spring.datasource.password}")
    private String prodpassword = "oracle_123";

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName = "oracle.jdbc.OracleDriver";


    @Autowired
    private Environment env;
    @Primary
    @Bean(name = "devDataSource")
    @Profile("dev")
    @ConfigurationProperties(prefix = "spring")
    public DataSource devDataSource() {


        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(devurl)
                .username(devusername)
                .password(devpassword)
                .build();

//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//        dataSource.setUrl(env.getProperty("oracle.datasource.url"));
//        dataSource.setUsername(env.getProperty("oracle.datasource.username"));
//        dataSource.setPassword(env.getProperty("oracle.datasource.password"));
//        return dataSource;
    }
    }


    @Primary
    @Bean(name = "stageDataSource")
    @Profile("stage")
    public DataSource stageDataSource() {


        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(stageurl)
                .username(stageusername)
                .password(stagepassword)
                .build();

    }

    @Primary
    @Bean(name = "prodDataSource")
    @Profile("prod")
    public DataSource prodDataSource() {

        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(produrl)
                .username(produsername)
                .password(prodpassword)
                .build();

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

//        return builder
//                .dataSource(devDataSource)
//                .packages("com.bsp.upgrade.entity")
//                .persistenceUnit("EsbServiceInf")
//                .build();

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        if (env.getActiveProfiles()[0].equals("dev")) {
            entityManagerFactoryBean.setDataSource(devDataSource);
        } else if (env.getActiveProfiles()[0].equals("stage")) {
            entityManagerFactoryBean.setDataSource(stageDataSource);
        } else if (env.getActiveProfiles()[0].equals("prod")) {
            entityManagerFactoryBean.setDataSource(prodDataSource);
        }

        entityManagerFactoryBean.setPackagesToScan("com.bsp.upgrade.entity");
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPersistenceUnitName("EsbServiceInf");

        HashMap<String, Object> properties = new HashMap<>();

        entityManagerFactoryBean.setJpaPropertyMap(properties);

        return entityManagerFactoryBean;
    }



    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
