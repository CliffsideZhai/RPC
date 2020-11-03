package com.cliffside;

import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cliffside
 * @date 2020-11-02 21:41
 */
@RestController
public class MainController {
    @GetMapping("/getHi")
    public String test(){


        return "你好哦";
    }
}
