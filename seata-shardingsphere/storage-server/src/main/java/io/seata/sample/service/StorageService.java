package io.seata.sample.service;

import io.seata.sample.entity.Storage;

import java.math.BigDecimal;

public interface StorageService {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    void decrease(Long productId, Integer count);


    //BigDecimal findById(Long productId);
}
