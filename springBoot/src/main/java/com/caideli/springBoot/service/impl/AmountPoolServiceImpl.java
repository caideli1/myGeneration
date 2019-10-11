package com.caideli.springBoot.service.impl;

import com.caideli.springBoot.base.CommonServiceImpl;
import com.caideli.springBoot.dao.AmountPoolDao;
import com.caideli.springBoot.model.AmountPool;
import com.caideli.springBoot.model.AmountPoolFlow;
import com.caideli.springBoot.service.AmountPoolFlowService;
import com.caideli.springBoot.service.AmountPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 10:57
 * 描述：
 */
@Slf4j
@Service
public class AmountPoolServiceImpl extends CommonServiceImpl<AmountPool> implements AmountPoolService {
    @Resource
    private AmountPoolDao amountPoolDao;
    @Autowired
    private AmountPoolFlowService amountPoolFlowService;
    @PostConstruct
    public void init() {
        super.commonMapper = amountPoolDao;
    }

    @Override
    public int backendUpdateAmount(Integer amountPoolId, Integer userId, BigDecimal changeAmount) {
        log.info("数据：amountPoolId="+amountPoolId+" userId="+userId+" changeAmount="+changeAmount);
        int result = amountPoolDao.backendUpdateAmount(amountPoolId,changeAmount);
        if (result==1){
            //记录充值记录
            AmountPool amountPool = this.selectByPrimaryKey(amountPoolId);
            AmountPoolFlow amountPoolFlow = new AmountPoolFlow();
            amountPoolFlow.setUserId(userId);
            amountPoolFlow.setAmountPoolId(amountPoolId);
            amountPoolFlow.setChangeAmount(changeAmount);
            amountPoolFlow.setNowOriginalAmount(amountPool.getOriginalAmount());
            amountPoolFlow.setLastRestAmount(amountPool.getOriginalAmount().subtract(changeAmount));
            amountPoolFlowService.insertSelective(amountPoolFlow);
            log.info("上一次的剩余金额："+amountPoolFlow.getLastRestAmount()+" 充值后的初始金额:"+amountPoolFlow.getNowOriginalAmount());
        }
        return result;
    }

    @Override
    public int userUpdateAmount(Integer amountPoolId, Integer userId, BigDecimal changeAmount, BigDecimal riskAmount) {
        int result = amountPoolDao.userUpdateAmount(amountPoolId,changeAmount,riskAmount);
        //资金池扣除成功，生成未激活钱包
        if (result==1){

        }else {//不成功，生成预通过钱包

        }
        return result;
    }
}
