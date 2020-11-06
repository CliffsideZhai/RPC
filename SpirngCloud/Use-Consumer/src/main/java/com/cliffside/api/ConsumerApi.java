package com.cliffside.api;

import com.cliffside.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author cliffside
 * @date 2020-11-05 22:14
 */
@FeignClient(name = "user-provider")
public interface ConsumerApi extends UserApi
{
}
