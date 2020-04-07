package com.caideli.springBoot.designModel.strategy.factoryRealize;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/3/25 14:07
 * 描述：现金收费抽象类
 */
public abstract class CashSuper {
    /**
     * 收取现金
     * @param money
     * @return
     */
    public abstract double acceptCash(double money);
}
