package com.cliffside.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author cliffside
 * @date 2020-11-04 9:28
 */
@Controller
public class RestController {
    @Autowired
    DiscoveryClient client;

    /**
     * DiscoveryClient接口的具体实现类
     */
    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    /**
     * ribbon的实现接口
     */
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/rest01")
    public Object rest01(){
        String url = "http://provider/getHi";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }
}
