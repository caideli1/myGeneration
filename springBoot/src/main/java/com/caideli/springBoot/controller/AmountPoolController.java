package com.caideli.springBoot.controller;

import com.caideli.springBoot.base.JsonResult;
import com.caideli.springBoot.service.AmountPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/26 15:46
 * 描述：
 */
@Slf4j
@RestController
public class AmountPoolController {
    @Autowired
    private AmountPoolService amountPoolService;

    /**
     * 后台充值资金池接口
     * @param amountPoolId
     * @param changeAmount
     * @return
     */
    @RequestMapping("/recharge")
    public JsonResult recharge(@RequestParam Integer amountPoolId,@RequestParam BigDecimal changeAmount) {
        log.info("--------充值开始--------");
        int result = amountPoolService.backendUpdateAmount(amountPoolId,1,changeAmount);
        log.info("--------充值结束--------");
        if (result!=1){
            return JsonResult.errorMsg("充值失败，请联系管理员!");
        }
        return JsonResult.ok();
    }

    @RequestMapping("/innitWallet")
    public JsonResult innitWallet(@RequestParam Integer productId,@RequestParam Integer userId,@RequestParam String applyNo,
                                  @RequestParam Integer score) {
        log.info("--------初始化钱包开始--------");
        //TODO 通过productId获取产品对象，拿到60天的产品周期
        //TODO 通过系统参数的枚举，获取系统资金池风险资金
//        int result = amountPoolService.userUpdateAmount(amountPoolId,1,changeAmount);
        log.info("--------初始化钱包结束--------");
        if (0!=1){
            return JsonResult.errorMsg("充值失败，请联系管理员!");
        }
        return JsonResult.ok();
    }
}
