package com.cliffside.api;

import com.cliffside.UserApi.Person;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cliffside
 * @date 2020-11-06 20:20
 */
@Component
public class UserProviderBackFactory implements FallbackFactory<ConsumerApi> {
    @Override
    public ConsumerApi create(Throwable throwable) {
        return new ConsumerApi() {

            @Override
            public Map<Integer, String> getMap(Integer id) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap2(Integer id, String name) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap3(Map<String, Object> map) {
                return null;
            }

            @Override
            public Map<Integer, String> postMap(Map<String, Object> map) {
                return null;
            }

            @Override
            public String alive() {
                System.out.println(throwable);
                if (throwable instanceof FeignException.InternalServerError){
                    return "服务器500" ;
                }else if (throwable instanceof HystrixTimeoutException){
                    return "连接超时了，我又降级了";
                }else {
                    return "因不可抗力，我再次降级了";
                }
            }

            @Override
            public String getById(Integer id) {
                return null;
            }

            @Override
            public Person postPserson(Person person) {
                return null;
            }
        };
    }
}
