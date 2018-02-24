package com.ehangtian.core.service;

import com.ehangtian.core.mapper.center.AccountBanlanceMapper;
import com.ehangtian.core.mapper.center.BfjTransUsedMapper;
import com.ehangtian.core.mapper.master.InstOrderMapper;
import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.BfjTransUsed;
import com.ehangtian.core.model.InstOrder;
import com.ehangtian.core.util.DateUtil;
import com.ehangtian.core.util.IdGen;
import com.ehangtian.core.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuai on 2018/1/4.
 */
@Service
public class InstOrderService {
    private final static Logger logger = LoggerFactory.getLogger(InstOrderService.class);
    @Autowired
    private InstOrderMapper instOrderMapper;

    @Autowired
    private BfjTransUsedMapper bfjTransUsedMapper;
    @Autowired
    private AccountBanlanceMapper accountBanlanceMapper;
     //从支付系统数据库查询交易明细
    public List<BfjTransUsed> findInstOrderBySubmitDate(String beginDate, String endDate) {
        List<InstOrder> instOrders = instOrderMapper.findInstOrderBySubmitDate(beginDate, endDate);
        List<BfjTransUsed> bfjTransUsedList = new ArrayList<>();
        if (instOrders != null) {
            for (InstOrder order : instOrders) {
                BfjTransUsed transUsed = new BfjTransUsed();
                transUsed.setTransUsedId(Long.parseLong("1"+IdGen.getId()));
                transUsed.setSourceUsedId(order.getInstOrderId());
                transUsed.setSubjectCode("00000023");
                transUsed.setSubjectName("备付金-用户备付金-现金");
                transUsed.setRecordDate(beginDate);
                if ("I".equals(order.getOrderType().trim())) {
                    transUsed.setWorkOrderCode("CZ");
                    transUsed.setTransFlag("1");
                    transUsed.setBfjUpLowFlag("1");
                    transUsed.setKhUpLowFlag("1");
                } else if ("O".equals(order.getOrderType().trim())) {
                    transUsed.setWorkOrderCode("TX");
                    transUsed.setTransFlag("0");
                    transUsed.setBfjUpLowFlag("2");
                    transUsed.setKhUpLowFlag("2");
                }
                transUsed.setBusinessType("8");
                transUsed.setCurrencyType("1");
                transUsed.setBankReference("0");
                transUsed.setAmount(order.getAmount());
                transUsed.setBankMoney(order.getAmount());
                bfjTransUsedList.add(transUsed);
            }
        }
        return bfjTransUsedList;
    }
    //插入备付金系统交易明细表
    public int addBfjTransUsedData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String beginDate = sdf.format(today);
        String endDate = sdf.format(DateUtil.getNextDay(today));
        List<BfjTransUsed> bfjTransUsedList = findInstOrderBySubmitDate("2018-01-09", "2018-01-10");
        if(bfjTransUsedList==null){
            bfjTransUsedList=new ArrayList<>();
        }
        logger.info("####[支付系统的数据]交易日期为：" + beginDate + ",查询出的交易的数据总条数为：" + bfjTransUsedList.size());
        int insertSuccessNum = 0;
        for (BfjTransUsed transUsed : bfjTransUsedList) {
            int insertNum = bfjTransUsedMapper.addBfjTransUsedData(transUsed);
            if (insertNum > 0) {
                insertSuccessNum += insertNum;
                logger.info("####[支付系统的数据]机构订单表（tt_inst_order）的INST_ORDER_ID=" + transUsed.getSourceUsedId() + ",备付金交易明细表（trans_used）的TRANS_USED_ID=" + transUsed.getTransUsedId());
            }else{
                logger.info("####[支付系统的数据]机构订单表（tt_inst_order）的INST_ORDER_ID=" + transUsed.getSourceUsedId()+",插入备付金系统交易明细失败");
            }
        }
                logger.info("####[支付系统的数据]插入成功的总数量为：" + insertSuccessNum);
        return insertSuccessNum;
    }

      //查询支付系统数据库的余额数据
       public AccountBalance findAccountBalanceByDate(String beginDate){
            List<Double> creditBalance= instOrderMapper.findAccountBalanceByDate(beginDate);
           if(creditBalance==null||(creditBalance.get(0)==0.00&&creditBalance.get(1)==0.00)){
               logger.info("#####无数据...");
               return null;
           }
           AccountBalance accountBalance=new AccountBalance();
             if(accountBalance!=null) {
                 accountBalance.setAccountBalanceId(Long.parseLong("1"+IdGen.getId()));
                 accountBalance.setRecordDate(beginDate);
                 accountBalance.setValidPrice(creditBalance.get(0)+creditBalance.get(1));
                 accountBalance.setAccount("0000000000000000");
                 accountBalance.setBusinessType("8");
                 accountBalance.setAccountNature("801");
             }
             return accountBalance;
        }

       //插入备付金系统余额表
       public int addBfjAccountBalance(){
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
           Date today = new Date();
           String beginDate = sdf.format(today);
           String endDate = sdf.format(DateUtil.getNextDay(today));
           int insertNum=0;
           AccountBalance accountBalance= findAccountBalanceByDate(beginDate);
             if(accountBalance!=null) {
                 logger.info("####[支付系统的数据]交易日期为：" + beginDate + ",查询出的余额为：" + accountBalance.getValidPrice());
                 insertNum = accountBanlanceMapper.addBfjAccountBalance(accountBalance);
                 if (insertNum > 0) {
                     logger.info("####[支付系统的数据]交易日期为：" + beginDate + ",插入备付金余额表成功");
                 } else {
                     logger.info("####[支付系统的数据]交易日期为：" + beginDate + ",插入备付金余额表失败");
                 }
             }
           return insertNum;
       }


}
