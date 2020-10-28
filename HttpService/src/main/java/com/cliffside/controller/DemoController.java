package com.cliffside.controller;

import com.cliffside.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cliffside
 * @date 2020-10-28 10:14
 *
 * 用HttpClient 就是用Java代码模拟
 * 浏览器对后端的url访问
 *
 */
@Controller
public class DemoController {


    @RequestMapping("/demo")
    @ResponseBody
    public String demo(String param){
        return "哈哈哈"+param ;
    }

    @RequestMapping("/demo2")
    @ResponseBody
    public User demo2(User user){
        return user ;
    }

    @RequestMapping("/demo03")
    @ResponseBody
    public List<User> demo03(){
        List<User> users = new ArrayList<>();
        users.add(0,new User(1,"上三"));
        users.add(1,new User(2,"李四"));
        return users;
    }

    /**
     * 流数据解析
     */
    @RequestMapping("/demo04")
    @ResponseBody
    public String demo04(HttpServletRequest request){
        Integer read = 0;
        try {
            read = request.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s1 = Integer.toString(read);
        return s1;
    }



}
