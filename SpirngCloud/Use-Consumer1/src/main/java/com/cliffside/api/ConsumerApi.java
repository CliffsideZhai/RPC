package com.cliffside.api;


import com.cliffside.UserApi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author cliffside
 * @date 2020-11-05 22:14
 */

@FeignClient(name = "user-provider",fallbackFactory = UserProviderBackFactory.class)
public interface ConsumerApi extends UserApi {

    /**
     * 这里 getMapping 是给Feign看的 get请求 user-provider/getMap?id={1}
     *
     * @param id
     * @return
     * @RequestParam("id") 也是给Feign看的
     * <p>
     * HttpClient Http协议
     */
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);


    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);
}


