package com.bsp.upgrade.controller;

import com.bsp.upgrade.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/allgtx")
@Slf4j
public class AllGTXController {

    @Autowired
    private EntityService entityService ;


    private String sName;
    private String eTime;


    @GetMapping("/{databaseName}/{serviceName}/{eventTime}")
    public List<Object[]> allGlobalalTrxIdByDateAndServiceName (@PathVariable("databaseName") String databaseName ,
                                                                @PathVariable("serviceName") String serviceName
            , @PathVariable("eventTime") String eventTime){
        List<Object[]> obj = entityService.allGlobalalTrxIdByDateAndServiceName(serviceName ,eventTime,databaseName);
        for (Object[] ob : obj) {
            sName=(String) ob[0];
            eTime=(String) ob[1] ;
        }
        return obj;

    }
}
