package com.ehangtian.core.mapper.center;

import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.InstOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangshuai on 2018/1/3.
 */

@Mapper
public interface AccountBanlanceMapper {
    AccountBalance findById(Long accountBalanceId);
    List<AccountBalance> findAlls();

    int addBfjAccountBalance(AccountBalance accountBalance);

}
