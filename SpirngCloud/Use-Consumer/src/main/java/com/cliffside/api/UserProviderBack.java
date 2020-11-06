package com.cliffside.api;

import com.cliffside.UserApi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cliffside
 * @date 2020-11-06 20:00
 */
//@Component
public class UserProviderBack implements ConsumerApi {
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
        return "降级了";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public Person postPserson(Person person) {
        return null;
    }
}
