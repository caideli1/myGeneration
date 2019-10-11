package com.caideli.springBoot.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/26 14:24
 * 描述：
 */
@Data
public class AmountPoolFlow implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 资金池id
     */
    private Integer amountPoolId;

    /**
     * 上一次剩余金额
     */
    private BigDecimal lastRestAmount;

    /**
     * 充值金额
     */
    private BigDecimal changeAmount;

    /**
     * 充值后金额
     */
    private BigDecimal nowOriginalAmount;

    /**
     * 是平台记操作人id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
