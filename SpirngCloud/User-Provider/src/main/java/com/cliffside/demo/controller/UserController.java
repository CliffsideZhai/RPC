package com.cliffside.demo.controller;

import com.cliffside.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cliffside
 * @date 2020-11-05 20:08
 */

@RestController
public class UserController implements UserApi {

    @Override
    public String alive() {
        return "哈哈哈我成功实现了接口编程";



    }
}
