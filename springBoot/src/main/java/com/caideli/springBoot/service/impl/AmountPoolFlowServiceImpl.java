package com.caideli.springBoot.service.impl;

import com.caideli.springBoot.base.CommonServiceImpl;
import com.caideli.springBoot.dao.AmountPoolFlowDao;
import com.caideli.springBoot.model.AmountPoolFlow;
import com.caideli.springBoot.service.AmountPoolFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/26 14:26
 * 描述：
 */
@Slf4j
@Service
public class AmountPoolFlowServiceImpl extends CommonServiceImpl<AmountPoolFlow> implements AmountPoolFlowService {
    @Resource
    private AmountPoolFlowDao amountPoolFlowDao;
    @PostConstruct
    public void init() {
        super.commonMapper = amountPoolFlowDao;
    }

}
