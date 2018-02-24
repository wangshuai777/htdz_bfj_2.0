package com.ehangtian.core.service;

import com.ehangtian.core.mapper.center.AccountBanlanceMapper;
import com.ehangtian.core.mapper.master.InstOrderMapper;
import com.ehangtian.core.mapper.slave.TransUsedMapper;
import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.InstOrder;
import com.ehangtian.core.model.TransUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenwei on 2017/12/29.
 */
@Service
public class HelloService {

    @Autowired
    private InstOrderMapper instOrderMapper;

    @Autowired
    private AccountBanlanceMapper accountBanlanceMapper;

    @Autowired
    private TransUsedMapper transUsedMapper;

    /*public InstOrder getHello(){
        return instOrderMapper.findByOrderNo("FI101010001091300820008");
    }*/

    public List<InstOrder> findAll(){
        return instOrderMapper.findAll();
    }

    public List<AccountBalance> findAlls(){
        return accountBanlanceMapper.findAlls();
    }

    public AccountBalance findById(Long accountBalanceId){
        return accountBanlanceMapper.findById(accountBalanceId);
    }

    public TransUsed findByTransId(Long transUsedId){
        return transUsedMapper.findById(transUsedId);
    }
}
