package com.van.controllers;

import com.van.services.DataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vly on 1/23/2016.
 */
@RestController
@RequestMapping("/")
public class Main {

    @Autowired
    DataAccessService das;

    @RequestMapping
    public Object index() {
        Map<String,List> results = new HashMap<>();
        results.put("default", das.getData(0));
        results.put("datasourceOne", das.getData(1));
        results.put("dataSourceTwo", das.getData(2));

        return results;
    }
}
