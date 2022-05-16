package com.caideli.springBoot.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 10:52
 * 描述：资金池
 */
@Data
public class AmountPool implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 初始化金额
     */
    private BigDecimal originalAmount;

    /**
     * 当前金额
     */
    private BigDecimal amount;

    /**
     * 已经使用金额
     */
    private BigDecimal usedAmount;

    /**
     * 状态：0 不通知 1 通知
     */
    private Integer status;

    private String img;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
