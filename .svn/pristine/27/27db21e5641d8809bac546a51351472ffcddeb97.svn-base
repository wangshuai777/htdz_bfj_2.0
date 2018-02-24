package com.ehangtian.core.mapper.slave;

import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.BfjTransUsed;
import com.ehangtian.core.model.TransUsed;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuai on 2018/1/3.
 */
public interface TransUsedMapper {
    TransUsed findById(Long transUsedId);
    List<Map<String,String>> getBfjSubjectCodeMap();
    List<Map<String,String>> getBfjSubjectNameMap();
    List<BfjTransUsed> getYfkTransUsedByDate(String date);
    List<AccountBalance> getYfkAccountBalance();
}
