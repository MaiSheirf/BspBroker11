    package com.bsp.upgrade.service;
    import com.bsp.upgrade.multiplecoimninterface.FullGourney;
    import com.bsp.upgrade.multiplecoimninterface.KeyPath;
    import com.bsp.upgrade.repository.EntityRepo;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.stereotype.Service;

    import java.util.List;





@Service
@Slf4j
@Configuration
@ComponentScan
public class EntityServiceImp implements EntityService {


    private final EntityRepo repositoryDev;
    private final EntityRepo repositoryStage;
     private final EntityRepo repositoryProd;

    @Autowired

    public EntityServiceImp(@Qualifier("devRepository") EntityRepo repositoryDev,
                      @Qualifier("stageRepository") EntityRepo repositoryStage,
                      @Qualifier("prodRepository") EntityRepo repositoryProd) {
        this.repositoryDev = repositoryDev;
        this.repositoryStage = repositoryStage;
        this.repositoryProd = repositoryProd;
    }

    @Override
    public List<String> getServiceNames(String regex, String dataBaseName) {
        EntityRepo entityRepo;
        switch (dataBaseName.toLowerCase()) {
            case "dev11":
                entityRepo = repositoryDev;
                break;
            case "stage11":
                entityRepo = repositoryStage;
                break;
            case "prod11":
                entityRepo = repositoryProd;
                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + dataBaseName);
        }
        return entityRepo.getServiceNames(regex);
    }

    @Override
    public List<FullGourney> fullJournyByGlobalTransaction(String globalTransactionId, String databaseName) {
        EntityRepo fullJourneyRepo;
        switch (databaseName.toLowerCase()) {
            case "dev11":
                fullJourneyRepo = repositoryDev;
                break;
            case "stage11":
                fullJourneyRepo = repositoryStage;
                break;
            case "prod11":
                fullJourneyRepo = repositoryProd;
                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + databaseName);
        }
        return fullJourneyRepo.fullJournyByGlobalTransactionID(globalTransactionId);
    }

    @Override
    public List<Object[]> allGlobalalTrxIdByDateAndServiceName(String serviceName, String eventTime , String databaseName) {
        EntityRepo allGTXRepo;
        switch (databaseName.toLowerCase()) {
            case "dev11":
                allGTXRepo = repositoryDev;
                break;
            case "stage11":
                allGTXRepo = repositoryStage;
                break;
            case "prod11":
                allGTXRepo = repositoryProd;
                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + databaseName);
        }
        return allGTXRepo.allGlobalalTrxIdByDateAndServiceName(serviceName,eventTime);
    }

    @Override
    public List<Object[]> allKeyPaths(String serviceName ,String datbaseName) {
        EntityRepo keyPathsRepo;
        switch (datbaseName.toLowerCase()) {
            case "dev11":
                keyPathsRepo = repositoryDev;
                break;
            case "stage11":
                keyPathsRepo = repositoryStage;
                break;
            case "prod11":
                keyPathsRepo = repositoryProd;
                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + datbaseName);
        }
        return keyPathsRepo.allKeyPaths(serviceName , datbaseName);
    }


}



