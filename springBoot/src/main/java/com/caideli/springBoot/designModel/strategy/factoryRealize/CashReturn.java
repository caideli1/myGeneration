package com.caideli.springBoot.designModel.strategy.factoryRealize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/3/25 14:17
 * 描述：返利收费子类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashReturn extends CashSuper{

    /**
     * 返利条件
     */
    private double moneyCondition = 0.0d;

    /**
     * 返利大小
     */
    private double moneyReturn = 0.0d;

    @Override
    public double acceptCash(double money) {
        double result = money;
        //如果大于返利条件，则需要减去返利值
        if (money>moneyCondition){
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
