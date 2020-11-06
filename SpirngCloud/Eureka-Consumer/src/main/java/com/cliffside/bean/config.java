package com.cliffside.bean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cliffside
 * @date 2020-11-03 21:02
 */
@Configuration
public class config {
        /**
     * @return 这里返回一个单例的RestTemplate
     */
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        return new RoundRobinRule();
        //return new RoundRobinRule();
    }
}
