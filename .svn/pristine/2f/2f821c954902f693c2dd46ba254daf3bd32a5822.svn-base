package com.ehangtian.consumer;

import com.ehangtian.interfaceMapper.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenwei on 17/12/31.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;


    @RequestMapping("/fegin")
    public String helloConsumer(){
        return helloService.hello();
    }
}
