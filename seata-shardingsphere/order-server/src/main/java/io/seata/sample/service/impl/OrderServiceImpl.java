package io.seata.sample.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.sample.mapper.OrderMapper;
import io.seata.sample.entity.Order;
import io.seata.sample.feign.AccountFeign;
import io.seata.sample.feign.StorageFeign;
import io.seata.sample.service.OrderService;

import java.math.BigDecimal;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderServiceImpl")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StorageFeign storageFeign;

    @Autowired
    private AccountFeign accountFeign;

    /**
     * 创建订单
     * @return
     * 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @GlobalTransactional(timeoutMills = 120000)
    public void create(Long productId,Long userId,int count) {
        try {
            LOGGER.info("------->交易开始");
            BigDecimal price = new BigDecimal(20);
            BigDecimal totalAmount = price.multiply(new BigDecimal(count));
            Order order = new Order();
            order.setCount(count);
            order.setProductId(productId);
            order.setUserId(userId);
            order.setMoney(totalAmount);

            //本地方法
            orderMapper.create(order);

            //远程方法 扣减库存
            String xid = RootContext.getXID();
            System.out.println(xid);
            storageFeign.decrease(order.getProductId(),order.getCount());

            //远程方法 扣减账户余额

            LOGGER.info("------->扣减账户开始order中");
            accountFeign.decrease(order.getUserId(),totalAmount);
            LOGGER.info("------->扣减账户结束order中");

            LOGGER.info("------->交易结束");
            LOGGER.info("------->XID为："+ RootContext.getXID());
        } catch (Exception e) {
            throw new RuntimeException("事务执行失败，回滚");
        }

    }

    /**
     * 修改订单状态
     */
    @Override
    public void update(Long userId,BigDecimal money,Integer status) {
        LOGGER.info("修改订单状态，入参为：userId={},money={},status={}",userId,money,status);
        orderMapper.update(userId,money,status);
    }
}
