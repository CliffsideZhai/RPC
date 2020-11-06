package com.cliffside.UserApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cliffside
 * @date 2020-11-05 22:00
 */
public interface UserApi {

    /**
     * 工作中有专门起一个公共api服务的吗
     *
     * 查看当前服务状态~~~
     * @return (* ￣3)(ε￣ *)
     */
    @GetMapping("/alive")
    public String alive();

    @GetMapping("/getById")
    public String getById(Integer id);


    @PostMapping("/postPserson")
    public Person postPserson(@RequestBody Person person);

}
