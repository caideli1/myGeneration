package com.caideli.springBoot.dao;

import com.caideli.springBoot.base.CommonMapper;
import com.caideli.springBoot.model.AmountPool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 10:54
 * 描述：
 */
@Mapper
public interface AmountPoolDao extends CommonMapper<AmountPool> {
    /**
     *  钱包生成，资金池扣钱核心sql(mysql计算加锁)
     *  主逻辑：1 实际剩余金额扣除 amount - changeAmount
     *          2 使用金额一致累计 usedAmount + changeAmount
     *          3 在风险范围内 amount - changeAmount > riskAmount
     * @param id
     * @param changeAmount 修改金额
     * @param riskAmount 风险控制金额
     * @return
     */
    int userUpdateAmount(@Param("id") Integer id, @Param("changeAmount") BigDecimal changeAmount, @Param("riskAmount") BigDecimal riskAmount);

    /**
     * 平台管理员充值金额(mysql计算加锁)
     * 主逻辑：1 新的初始金额变动：originalAmount = amount + changeAmount
     *         2 实际金额变为当前充值后的初始金额 amount = amount + changeAmount
     * @param id
     * @param changeAmount
     * @return
     */
    int backendUpdateAmount(@Param("id") Integer id, @Param("changeAmount") BigDecimal changeAmount);
}
