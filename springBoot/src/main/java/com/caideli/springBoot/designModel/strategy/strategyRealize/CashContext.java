package com.caideli.springBoot.designModel.strategy.strategyRealize;

import com.caideli.springBoot.designModel.strategy.factoryRealize.CashNomal;
import com.caideli.springBoot.designModel.strategy.factoryRealize.CashRebate;
import com.caideli.springBoot.designModel.strategy.factoryRealize.CashReturn;
import com.caideli.springBoot.designModel.strategy.factoryRealize.CashSuper;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/2 13:55
 * 描述：
 */
public class CashContext {
    CashSuper cs = null;
    public CashContext(String type){
        switch (type){
            case "正常收费":
                cs = new CashNomal();
                break;
            case "满300返100":
                cs = new CashReturn(300,100);
                break;
            case "打八折":
                cs = new CashRebate(0.8);
                break;
        }
    }
    public double getResult(double money){
        return cs.acceptCash(money);
    }
}
