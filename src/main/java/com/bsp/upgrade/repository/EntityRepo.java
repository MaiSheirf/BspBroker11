package com.bsp.upgrade.repository;

import com.bsp.upgrade.entity.EsbServiceInf;
import com.bsp.upgrade.multiplecoimninterface.FullGourney;
import com.bsp.upgrade.multiplecoimninterface.KeyPath;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EntityRepo extends JpaRepository<EsbServiceInf,String> {

    @Query(value = "select distinct service_name serviceName from ESB_SERVICE_INF where UPPER(service_name) like  UPPER(?1) and isReadable=1 order by 1 asc ",
            nativeQuery = true)
    List<String> getServiceNames (String serviceName);


    @Query(value ="select std.payload payloadData, err.PAYLOAD payloadDataErr, serEve.PAYLOAD_ID payloadId," +
            " serEve.EVENT_NAME ServiceName , serEve.EVENT_TIME EventTime, serEve.EVENT_KEY1 EventKey1," +
            " serEve.EVENT_KEY2 EventKey2, serEve.EVENT_KEY3 EventKey3, serEve.EVENT_KEY4 EventKey4, " +
            "serEve.EVENT_KEY5 EventKey5, serEve.EVENT_STATUS eventStatus  " +
            "from  ESB_SERVICE_EVT serEve inner join ESB_SERVICE_TRX trans on " +
            "trans.TRANSACTION_ID = serEve.TRANSACTION_ID left outer JOIN " +
            "ESB_PAYLOAD_STD std ON serEve.PAYLOAD_ID = std.PAYLOAD_ID left outer join " +
            "ESB_PAYLOAD_ERR err on serEve.PAYLOAD_ID = err.PAYLOAD_ID where " +
            "trans.GLOBAL_TRANSACTION_ID = :globalTransactionId order by serEve.EVENT_TIME asc ",
            nativeQuery = true)
    List<FullGourney> fullJournyByGlobalTransactionID(String globalTransactionId);

    @Query(value = "select distinct tx.GLOBAL_TRANSACTION_ID RequestId , tx.START_TIME ReguestTime from " +
            "ESB_SERVICE_TRX tx ,(select distinct serTrans.GLOBAL_TRANSACTION_ID globalTransaction from " +
            " ESB_SERVICE_EVT serEve inner join ESB_SERVICE_INF serInfo on " +
            "serEve.SERVICE_ID = serInfo.SERVICE_ID  inner join ESB_SERVICE_TRX serTrans on " +
            "serEve.TRANSACTION_ID = serTrans.TRANSACTION_ID  where 1=1 and serInfo.SERVICE_NAME =:serviceName" +
            " and serEve.EVENT_TIME like :eventTime%)GTX where  GTX.globalTransaction = tx.TRANSACTION_ID " +
            "order by ReguestTime desc", nativeQuery = true)
    List<Object[]> allGlobalalTrxIdByDateAndServiceName (String serviceName , String eventTime);

    @Query(value = "SELECT inf.service_name,inf.service_operation,inf.service_key1_path,inf.service_key2_path,inf.service_key3_path,inf.service_key4_path,\n" +
            "inf.service_key5_path FROM esb_service_inf inf\n" +
            "WHERE\n" +
            "    inf.service_name LIKE '%getwifilogsRequest%' " ,nativeQuery = true)
    List<Object[]> allKeyPaths(String serviceName ,String datbaseName);


}
