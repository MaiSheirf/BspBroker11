package com.bsp.upgrade.controller;

import com.bsp.upgrade.multiplecoimninterface.FullGourney;
import com.bsp.upgrade.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fulljourney")
@Slf4j
public class FullJourneyController {

    @Autowired
    private EntityService entityService ;

    @GetMapping("/{databaseName}/{gtx}")
    public List<FullGourney> fullJournyByGlobalTransactionId (@PathVariable("databaseName") String databaseName ,
                                                              @PathVariable("gtx") String globalTransactionId
    ) {
        return entityService.fullJournyByGlobalTransaction(globalTransactionId, databaseName);

    }
}
