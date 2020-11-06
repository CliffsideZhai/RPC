package com.cliffside.UserApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cliffside
 * @date 2020-11-05 22:00
 */
@RequestMapping("/User")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

}
