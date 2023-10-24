package com.bsp.upgrade.repository;

import com.bsp.upgrade.entity.EsbServiceInf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepo extends JpaRepository<EsbServiceInf,String> {

    @Query("select distinct e.serviceName from EsbServiceInf e where UPPER(e.serviceName) like UPPER( ?1) and e.isReadable=1 order by 1 asc")
    List<String> getServiceNames (String serviceName);
}
