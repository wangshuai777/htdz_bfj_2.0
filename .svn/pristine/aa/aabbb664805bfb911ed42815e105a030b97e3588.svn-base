package com.ehangtian.interfaceMapper;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenwei on 17/12/31.
 */
@FeignClient("HTDZ-BFJ")
public interface HelloService {

    @RequestMapping("/bfj/h1")
    String hello();
}
