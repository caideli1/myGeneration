package com.caideli.springBoot.controller;


import com.caideli.springBoot.service.AmountPoolService;

import java.math.BigDecimal;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 14:30
 * 描述：
 */
public class UserThreadConsumer implements Runnable {

    private AmountPoolService amountPoolService;
    private Integer id;
    private BigDecimal changeAmount;
    private BigDecimal riskAmount;
    private Integer i;

    @Override
    public void run() {
        System.out.println("第"+i+"个人："+"-------------执行开始------------");
//        int result = amountPoolService.updateAmount(id,changeAmount,riskAmount);
        if (0==1){
            System.out.println("执行成功。");
        }else {
            System.out.println("额度不够，执行失败。");
        }
        System.out.println("第"+i+"个人："+"-------------执行结束------------结果："+0);
    }

    public UserThreadConsumer(AmountPoolService amountPoolService, Integer id, BigDecimal changeAmount, BigDecimal riskAmount, Integer i) {
        this.amountPoolService = amountPoolService;
        this.id = id;
        this.changeAmount = changeAmount;
        this.riskAmount = riskAmount;
        this.i = i;
    }
}
