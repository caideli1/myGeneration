package com.caideli.springBoot.designModel.strategy.strategyRealize;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/2 11:47
 * 描述：
 */
public class MainTest {
    public static void main(String[] args) {
        CashContext cashContext = new CashContext("满300返100");
        //获取正常的收费金额
        double actualAcceptMoney = cashContext.getResult(300);
    }

}
