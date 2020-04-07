package com.caideli.springBoot.designModel.strategy.factoryRealize;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/2 11:39
 * 描述：
 */
public class CashFactory {
    /**
     * 现金收取工厂
     * @param type
     * @return
     */
    public static CashSuper createCashAccept(String type){
        CashSuper cs = null;
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
        return cs;
    }
}
