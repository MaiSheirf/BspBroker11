package com.bsp.upgrade.service;

import com.bsp.upgrade.multiplecoimninterface.FullGourney;
import com.bsp.upgrade.multiplecoimninterface.KeyPath;

import java.util.List;

public interface EntityService {

    List<String> getServiceNames(String regex, String databaseName);

    List<FullGourney> fullJournyByGlobalTransaction(String globalTransactionId , String databaseName);

    List<Object[]> allGlobalalTrxIdByDateAndServiceName (String serviceName , String eventTime , String databaseName);

    List<Object[]> allKeyPaths(String serviceName ,String datbaseName);
}
