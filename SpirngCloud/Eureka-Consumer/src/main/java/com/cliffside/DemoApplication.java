package com.cliffside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    /**
//     * @return 这里返回一个单例的RestTemplate
//     */
//    @Bean
//    RestTemplate getRestTemplate(){
//
//        return new RestTemplate();
//    }

}
