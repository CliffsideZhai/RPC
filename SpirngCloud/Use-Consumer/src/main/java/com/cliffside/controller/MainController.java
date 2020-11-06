package com.cliffside.controller;

import com.cliffside.api.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author cliffside
 * @date 2020-11-05 20:31
 */
@RestController
public class MainController {
    @Autowired
    ConsumerApi api;

    @GetMapping("/alive")
    public String alive(){
        return api.alive();

    }

}
