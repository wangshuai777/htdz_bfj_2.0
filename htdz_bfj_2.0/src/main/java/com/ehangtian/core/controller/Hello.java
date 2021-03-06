package com.ehangtian.core.controller;

import com.ehangtian.core.Entity.ConnectionProperties;
import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.BfjTransUsed;
import com.ehangtian.core.model.InstOrder;
import com.ehangtian.core.model.TransUsed;
import com.ehangtian.core.service.HelloService;
import com.ehangtian.core.service.InstOrderService;
import com.ehangtian.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenwei on 2017/12/29.
 */
@RestController
@RequestMapping("/test")
public class Hello {

    private final static Logger logger = LoggerFactory.getLogger(Hello.class);

    @Autowired
    private HelloService helloService;
    @Autowired
    private InstOrderService instOrderService;
    @Autowired
    private ConnectionProperties properties;


    @RequestMapping("/h1")
    public String hello(){
        logger.info("#############"+properties.getIp()+":"+properties.getPort());
        return "hello world!";
    }

   @RequestMapping("/h2")
    public AccountBalance hello2(){
        return helloService.findById(563221L);
    }

    @RequestMapping("/h3")
    public List<InstOrder> hello3(){
        return helloService.findAll();
    }

    @RequestMapping("/h4")
    public List<AccountBalance> hello4(){
        return helloService.findAlls();
    }

    @RequestMapping("/h5")
    public TransUsed hello5(){
        return helloService.findByTransId(23528L);
    }

    @RequestMapping("/h6")
    public List<BfjTransUsed> hello6(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date today=new Date();
        String beginDate=sdf.format(today);
        String endDate=sdf.format(DateUtil.getNextDay(today));
        return instOrderService.findInstOrderBySubmitDate(beginDate, endDate);
    }

    @RequestMapping("/h7")
    public int hello7(){
        return instOrderService.addBfjTransUsedData();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate=new Date();
        System.out.println(sdf.format(beginDate));
        System.out.println(sdf.format(DateUtil.getNextDay(beginDate)));
    }
}
