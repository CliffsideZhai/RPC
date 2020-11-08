package com.cliffside.controller;


import com.cliffside.UserApi.Person;
import com.cliffside.UserApi.UserApi;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cliffside
 * @date 2020-11-05 20:08
 */

@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;


    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {

        try {
            System.out.println("准备睡");

            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i = count.getAndIncrement();
        System.out.println("====好的第：" + i + "次调用");
        return "我是provider1 port:" + port;
    }

    @Override
    public String getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }



    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, "mmeme");
    }
    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id,String name) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap2")
    public Map<Integer, String> postMap2(@RequestBody Person person) {
        // TODO Auto-generated method stub
        System.out.println(ToStringBuilder.reflectionToString(person));
        return Collections.singletonMap(person.getId(), person.getName());
    }

    @Override
    public Person postPserson(@RequestBody Person person) {
        System.out.println(ToStringBuilder.reflectionToString(person));
        return person;
    }
}
