    package com.bsp.upgrade.service;

    import org.hibernate.Session;
    import java.sql.Statement;
    import com.bsp.upgrade.repository.EntityRepo;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityManagerFactory;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Service;

    import java.sql.SQLException;
    import java.util.List;





@Service
@Slf4j
@Configuration
@ComponentScan
public class EntityServiceImp implements EntityService {


    @Autowired
    private EntityRepo userRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @Query(value = "select distinct service_name serviceName from ESB_SERVICE_INF where UPPER(service_name) like  UPPER(?1) and isReadable=1 order by 1 asc ",
            nativeQuery = true)
    public List<String> getServiceNames(String regex, String dataBaseName) {
        EntityRepo entityRepo;
        switch (dataBaseName) {
            case "dev11":
                setEntityManagerDataSource("dev11");
                entityRepo = userRepository;
                break;
//            case "stage11":
//                setEntityManagerDataSource("stage11");
//                break;
//            case "prod11":
//                setEntityManagerDataSource("prod11");
//                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + dataBaseName);
        }
        return entityRepo.getServiceNames(regex);
    }

    private void setEntityManagerDataSource(String databaseName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try (Statement statement = connection.createStatement()) {
                statement.execute("ALTER SESSION SET CURRENT_SCHEMA=" + databaseName);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to set database schema", e);
            }
        });
        entityManager.close();
    }
}



