package com.ehangtian.core.service;

import com.ehangtian.core.mapper.center.AccountBanlanceMapper;
import com.ehangtian.core.mapper.center.BfjTransUsedMapper;
import com.ehangtian.core.mapper.slave.TransUsedMapper;
import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.BfjTransUsed;
import com.ehangtian.core.util.IdGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuai on 2018/1/10.
 */
//预付卡数据
@Service
public class YkfTransDataService {

    private final static Logger logger = LoggerFactory.getLogger(InstOrderService.class);

    @Autowired
    TransUsedMapper transUsedMapper;

    @Autowired
    private BfjTransUsedMapper bfjTransUsedMapper;

    @Autowired
    private AccountBanlanceMapper accountBanlanceMapper;

    //获取备付金编码Map
    public  List<Map<String,String>> getBfjSubjectCodeMap(){
        return transUsedMapper.getBfjSubjectCodeMap();
    }
    public List<Map<String,String>> getBfjSubjectNameMap(){
        List<Map<String,String>> nameMaps =transUsedMapper.getBfjSubjectNameMap();
        return nameMaps;
    }

    //获取预付卡交易明细数据

    public List<BfjTransUsed> getYfkTransUsedByDate(String date){

        List<BfjTransUsed> bfjTransUsedList=transUsedMapper.getYfkTransUsedByDate(date);
        if(bfjTransUsedList==null){
            bfjTransUsedList=new ArrayList<>();
        }
            List<Map<String,String>> codeMaps= getBfjSubjectCodeMap();
            List<Map<String,String>> nameMaps= getBfjSubjectNameMap();
        for(BfjTransUsed transUsed:bfjTransUsedList){
            String subjectCode=null;
            for(Map<String,String> map:codeMaps){
                if(map.get("OTHER_SUB_CODE").equals(transUsed.getSubjectCode())){
                    subjectCode= map.get("BFJ_SUB_CODE");
                    break;
                }
            }
            transUsed.setTransUsedId(Long.parseLong("0" + IdGen.getId()));
            transUsed.setRecordDate(date);
            String subjectName ="";
            if(subjectCode==null){
                subjectCode="00000099";
                subjectName = "其它";
            }else{
                for(Map<String,String> map:nameMaps){
                    if(map.get("SUBJECT_CODE").equals(subjectCode)){
                        subjectName= map.get("SUBJECT_NAME");
                        break;
                    }
                }
            }
            transUsed.setSubjectCode(subjectCode);
            transUsed.setSubjectName(subjectName);
        }
        return bfjTransUsedList;
    }

        //插入备付金系统交易明细表
        public int addBfjTransUsedData() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String beginDate = sdf.format(today);
            List<BfjTransUsed> dataList=getYfkTransUsedByDate("2015-08-06");
            if(dataList==null){
                dataList=new ArrayList<>();
            }
            logger.info("####[预付卡系统的数据]交易日期为：" + beginDate + ",查询出的交易的数据总条数为：" + dataList.size());
            int insertSuccessNum = 0;
            for (BfjTransUsed transUsed : dataList) {
                int insertNum = bfjTransUsedMapper.addBfjTransUsedData(transUsed);
                if (insertNum > 0) {
                    insertSuccessNum += insertNum;
                    logger.info("####[预付卡系统的数据]业务订单号的WORK_ORDER_CODE=" + transUsed.getWorkOrderCode() + ",备付金交易明细表（trans_used）的TRANS_USED_ID=" + transUsed.getTransUsedId());
                }else{
                    logger.info("####[预付卡系统的数据]业务订单号的WORK_ORDER_CODE=" + transUsed.getWorkOrderCode()+",插入备付金系统交易明细失败");
                }
            }
            logger.info("####[预付卡系统的数据]插入成功的总数量为：" + insertSuccessNum);
            return insertSuccessNum;

        }

       //查询预付卡系统余额数据
       public List<AccountBalance> getYfkAccountBalance(String date){
           List<AccountBalance> accountBalances=  transUsedMapper.getYfkAccountBalance();
           if(accountBalances!=null) {
              for(AccountBalance accountBalance:accountBalances) {
                  accountBalance.setAccountBalanceId(Long.parseLong("0" + IdGen.getId()));
                  accountBalance.setRecordDate(date);
                  accountBalance.setBusinessType("A");
              }
           }
           return accountBalances;
       }

    //插入备付金系统余额表
    public int addBfjAccountBalance(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String beginDate = sdf.format(today);
        int insertNum=0;
        List<AccountBalance> accountBalances= getYfkAccountBalance(beginDate);
        int insertSuccessNum = 0;
        if(accountBalances!=null){
                logger.info("####[预付卡系统的数据]交易日期为：" + beginDate + ",查询出的数据总条数：" + accountBalances.size());
            for(AccountBalance accountBalance:accountBalances) {
                insertNum = accountBanlanceMapper.addBfjAccountBalance(accountBalance);
                if (insertNum > 0) {
                    insertSuccessNum += insertNum;
                    logger.info("####[预付卡系统的数据]账号(account)=" + accountBalance.getAccount() + ",查询出的余额(validPrice)=" + accountBalance.getValidPrice());
                } else {
                    logger.info("####[预付卡系统的数据]账号(account)=" + accountBalance.getAccount() + ",插入备付金余额表失败");
                }
            }
        }
        logger.info("####[预付卡系统的数据]插入成功的总数量为：" + insertSuccessNum);
        return insertNum;
    }

}
