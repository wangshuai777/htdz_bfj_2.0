package com.ehangtian.core.mapper.master;

import com.ehangtian.core.model.AccountBalance;
import com.ehangtian.core.model.InstOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuai on 2018/1/2.
 */
@Mapper
public interface InstOrderMapper {

    List<InstOrder> findAll();

    List<InstOrder> findInstOrderBySubmitDate(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    List<Double> findAccountBalanceByDate(@Param("beginDate") String beginDate);



}
