package com.bsp.upgrade.controller;

import com.bsp.upgrade.multiplecoimninterface.KeyPath;
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
@RequestMapping("/keypath")
@Slf4j
public class KeyPathController {

    @Autowired
    private EntityService entityService ;

    private String sName;


    @GetMapping("/{databaseName}/{serviceName}")
    public List<Object[]> allKeyPaths(@PathVariable("serviceName") String serviceName ,
                                     @PathVariable("databaseName") String databaseName

    ){


        serviceName = "%" + serviceName + "%";
        List<Object[]> obj = entityService.allKeyPaths(serviceName ,databaseName);
        for (Object[] ob : obj) {
            sName=(String) ob[0];
        }
        return obj;


    }
}
