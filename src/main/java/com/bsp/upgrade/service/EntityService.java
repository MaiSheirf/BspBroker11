package com.bsp.upgrade.service;

import java.util.List;

public interface EntityService {

    List<String> getServiceNames(String regex, String databaseName);
}
