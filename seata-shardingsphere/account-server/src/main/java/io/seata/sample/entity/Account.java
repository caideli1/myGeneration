package io.seata.sample.entity;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class Account {


    /**用户id*/
    private Long userId;

    /**总额度*/
    private BigDecimal amount;

}
