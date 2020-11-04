package com.cliffside;

import com.cliffside.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cliffside
 * @date 2020-11-02 21:41
 */
@RestController
public class MainController {

    @Value("${server.port}")
    String port;
    @Autowired
    HealthStatusService healthStatusService;

    @GetMapping("/getHi")
    public String test(){

        return "你好哦port"+port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
