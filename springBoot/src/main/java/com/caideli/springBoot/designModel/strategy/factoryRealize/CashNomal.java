package com.caideli.springBoot.designModel.strategy.factoryRealize;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/3/25 14:09
 * 描述：正常收费子类
 */
public class CashNomal extends CashSuper{
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
