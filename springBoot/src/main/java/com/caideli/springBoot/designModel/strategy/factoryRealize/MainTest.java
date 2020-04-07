package com.caideli.springBoot.designModel.strategy.factoryRealize;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/2 11:47
 * 描述：
 */
public class MainTest {
    public static void main(String[] args) {
        CashSuper cashSuper = CashFactory.createCashAccept("正常收费");
        //获取正常的收费金额
        double actualAcceptMoney = cashSuper.acceptCash(300);
    }

}
