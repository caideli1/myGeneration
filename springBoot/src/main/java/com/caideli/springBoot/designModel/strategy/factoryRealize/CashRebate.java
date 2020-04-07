package com.caideli.springBoot.designModel.strategy.factoryRealize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/3/25 14:13
 * 描述：打折收费子类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashRebate extends CashSuper{

    /**
     * 打折属性
     */
    private double moneyRebate = 1d;

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
