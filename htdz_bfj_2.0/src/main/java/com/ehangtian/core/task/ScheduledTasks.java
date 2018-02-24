package com.ehangtian.core.task;

import com.ehangtian.core.service.InstOrderService;
import com.ehangtian.core.service.YkfTransDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangshuai on 2018/1/4.
 */
@Component
public class ScheduledTasks {

    private final static Logger logger = LoggerFactory.getLogger(InstOrderService.class);

    @Autowired
    private InstOrderService instOrderService;
    @Autowired
    private YkfTransDataService ykfTransDataService;

    @Scheduled(cron = "0 30 0 * * ?") // 每天0:30执行一次
    public void addBfjTransUsedDataFromZF() {
        logger.info("[支付系统数据]每日交易明细数据插入，定时任务启动..");
        instOrderService.addBfjTransUsedData();
        logger.info("[支付系统数据]每日交易明细数据插入，定时任务结束..");
    }

    @Scheduled(cron = "0 30 0 * * ?") // 每天0:30执行一次
    public void addBfjAccountBalanceFromZF() {
        logger.info("[支付系统数据]每日余额数据插入，定时任务启动..");
        instOrderService.addBfjAccountBalance();
        logger.info("[支付系统数据]每日余额数据插入，定时任务结束..");
    }

    @Scheduled(cron = "0 30 0 * * ?") // 每天0:30执行一次
    public void addBfjTransUsedDataFromYFK() {
        logger.info("[预付卡系统数据]每日交易明细数据插入，定时任务启动..");
        ykfTransDataService.addBfjTransUsedData();
        logger.info("[预付卡系统数据]每日交易明细数据插入，定时任务结束..");
    }

    @Scheduled(cron = "0 30 0 * * ?") // 每天0:30执行一次
    public void addBfjAccountBalanceFromYFK() {
        logger.info("[预付卡系统数据]每日余额数据插入，定时任务启动..");
        ykfTransDataService.addBfjAccountBalance();
        logger.info("[预付卡系统数据]每日余额数据插入，定时任务结束..");
    }

}
