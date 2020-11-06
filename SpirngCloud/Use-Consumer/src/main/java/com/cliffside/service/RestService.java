package com.cliffside.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author cliffside
 * @date 2020-11-06 20:59
 */
@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://user-provider/alive";
        String forObject = restTemplate.getForObject(url, String.class);

        return forObject;
    }

    public String back(){
        return "我又降级啦嘿嘿嘿";
    }
}