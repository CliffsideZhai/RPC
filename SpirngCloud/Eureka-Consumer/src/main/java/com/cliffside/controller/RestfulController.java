package com.cliffside.controller;

import com.cliffside.bean.Person;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @author cliffside
 * @date 2020-11-04 9:28
 */
@RestController
public class RestfulController {
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
        System.out.println("*****************************");
        System.out.println(forObject);
        System.out.println("_____________________________");
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity);
        return forObject;
    }

    @GetMapping("/rest02")
    public Object rest02(){
        String url = "http://provider/getMap";
        Map<String,String> forObject = restTemplate.getForObject(url, Map.class);
        System.out.println(forObject);
        System.out.println("_____________________________");
        System.out.println(forObject);
        return forObject;
    }

    @GetMapping("/rest03")
    public String rest03(){
        String url = "http://provider/health";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        System.out.println(forEntity);
        return "测试health请求";
    }

    @GetMapping("/rest04")
    public String rest04(){
        String url = "http://provider/getPerson";
        ResponseEntity<Person> person = restTemplate.getForEntity(url, Person.class);
        System.out.println(person);
        System.out.println("_____________________________");
        System.out.println(person.toString());
        return person.toString();
    }


    @GetMapping("/client14")
    public Object client14() {
        // 自动处理URL
        String url ="http://provider/getPerson2?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "xiao66");

        Person object = restTemplate.getForObject(url, Person.class,map);

        return object;
    }


    @GetMapping("/client15")
    public Object client15(HttpServletResponse response) throws Exception {
        // 自动处理URL
        String url ="http://provider/postLocation";


        Map<String, String> map = Collections.singletonMap("name", " memeda");
        URI location = restTemplate.postForLocation(url, map, Person.class);



        response.sendRedirect(location.toURL().toString());
        return null;


    }

}
