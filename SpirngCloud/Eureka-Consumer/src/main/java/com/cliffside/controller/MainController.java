package com.cliffside.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author cliffside
 * @date 2020-11-02 21:41
 */
@RestController
public class MainController {


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

    @GetMapping("/getHi")
    public String test(){


        return "你好哦";
    }


    @GetMapping("/client")
    public String test01(){

        List<String> services = client.getServices();
        for (String str:services
             ) {
            System.out.println(str);
        }
        return "你好哦";
    }

    @GetMapping("/client2")
    public String test02() throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        String provider = objectMapper.writeValueAsString(
                client.getInstances("provider"));
        return provider;
    }

    @GetMapping("/client3")
    public String test03() throws JsonProcessingException {
        List<ServiceInstance> provider = client.getInstances("provider");
        for (ServiceInstance ins: provider
             ) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        return "测试获取client的实例";
    }

    /**
     * 极简版实现远程服务调用
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/client4")
    public String test04() throws JsonProcessingException {
        //获取一个具体服务
        //List<InstanceInfo> provider = eurekaClient.getInstancesById("localhost:provider:8000");
        //使用服务名获取服务列表
        List<InstanceInfo> provider = eurekaClient.getInstancesByVipAddress("provider", false);
        for (InstanceInfo ins: provider
        ) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }

        if (provider.size()>0){
            InstanceInfo instanceInfo = provider.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP){
                String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()+"/getHi";
                System.out.println("url:"+url);

                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);
                System.out.println("响应回来时"+forObject);
            }

        }
        return "";
    }

    /**
     * 使用ribbon实现负载均衡
     * @return
     * @throws JsonProcessingException
     *
     * ribbon实现客户端的负载均衡，因为provider可能有多个服务
     * ribbon有负载均衡策略进行选择性的choose一个服务
     * 同时过滤掉了down了的节点
     */
    @GetMapping("/client5")
    public String test5() throws JsonProcessingException {

        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");

        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println("响应回来时" + forObject);

        return "";
    }
}
