package io.seata.sample.service;

import java.math.BigDecimal;

public interface OrderService {

    /**
     * 创建订单
     * @return
     */
    void create(Long productId,Long userId,int count);

    /**
     * 修改订单状态
     * @param userId
     * @param money
     * @param status
     */
    void update(Long userId,BigDecimal money,Integer status);
}
