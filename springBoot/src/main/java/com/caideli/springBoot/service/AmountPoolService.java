package com.caideli.springBoot.service;


import com.caideli.springBoot.base.CommonService;
import com.caideli.springBoot.model.AmountPool;

import java.math.BigDecimal;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 10:57
 * 描述：
 */
public interface AmountPoolService extends CommonService<AmountPool> {

    /**
     * 后台手动新增金额
     * @param amountPoolId
     * @param userId 操作人Id
     * @param changeAmount
     * @return
     */
    int backendUpdateAmount(Integer amountPoolId, Integer userId,BigDecimal changeAmount);
    /**
     * 生成钱包扣除资金池的金额
     * @param amountPoolId
     * @param userId 用户Id
     * @param changeAmount
     * @param riskAmount
     * @return
     */
    int userUpdateAmount(Integer amountPoolId, Integer userId,BigDecimal changeAmount, BigDecimal riskAmount);
}
