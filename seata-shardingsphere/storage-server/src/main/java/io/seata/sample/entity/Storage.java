package io.seata.sample.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Storage {

    private Long id;

    /**产品id*/
    private Long productId;

    /**库存数量*/
    private Integer total;

    private BigDecimal price;

}
