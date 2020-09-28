package io.seata.sample.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.sample.entity.Account;
import io.seata.sample.mapper.AccountMapper;
import io.seata.sample.feign.OrderFeign;
import java.math.BigDecimal;

import io.seata.sample.service.AccountService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountServiceImpl")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OrderFeign orderFeign;

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        String xid = RootContext.getXID();
        LOGGER.info("------->扣减账户开始account中,xid:{}", xid);
        //模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        BigDecimal amount = accountMapper.findByUserId(userId);
        if (amount.compareTo(money) < 0) {
            throw new RuntimeException("账户余额不足");
        }
        accountMapper.decrease(userId,money);
        LOGGER.info("------->扣减账户结束account中");

//        //修改订单状态，此调用会导致调用成环
//        LOGGER.info("修改订单状态开始");
//        String mes = orderFeign.update(userId, money.multiply(new BigDecimal("0.09")),0);
//        LOGGER.info("修改订单状态结束：{}",mes);
    }
}
