package com.bsp.upgrade.controller;

import com.bsp.upgrade.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/serinfo")
@Slf4j
public class EntityController {

    @Autowired
    private EntityService entityService ;

    @GetMapping("/{databaseName}/{serviceName}")
    public List<String> getServiceName (@PathVariable("databaseName") String databaseName,
                                        @PathVariable("serviceName") String serviceName
                                        ){
        List<String> serviceNames = new ArrayList<>();
        serviceName = "%" + serviceName + "%";
        serviceNames =  entityService.getServiceNames(serviceName,databaseName);

        return serviceNames;
    }

}
