package com.cliffside.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cliffside
 * @date 2020-11-03 19:18
 */
@RestController
public class RibbonController {

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

    @GetMapping("/ribbon01")
    public String client01(){

        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");

        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getHi";

        RestTemplate restTemplate = new RestTemplate();

        String forObject = restTemplate.getForObject(url, String.class);

        System.out.println("响应回来时" + forObject);

        return "这是ribbon01";
    }

    /**
     * 手动进行负载均衡
     *
     * @return
     */
    AtomicInteger atomicInteger = new AtomicInteger();
    int andIncrement = atomicInteger.getAndIncrement();
    @GetMapping("/ribbon02")
    public String client02(){

        List<ServiceInstance> provider = client.getInstances("provider");
        //随机
        int i = new Random().nextInt(provider.size());
        ServiceInstance instance2 = provider.get(i);


        //轮询
        andIncrement = atomicInteger.getAndIncrement();
        System.out.println(provider.size());
        System.out.println(andIncrement);
        int a = andIncrement % provider.size();
        System.out.println(a);
        ServiceInstance instance1 = provider.get(a);
//        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");


        String url = "http://" + instance1.getHost() + ":" + instance1.getPort() + "/getHi";

        RestTemplate restTemplate = new RestTemplate();

        String forObject = restTemplate.getForObject(url, String.class);

        System.out.println("响应回来时" + forObject);

        return "这是ribbon01";
    }
}
