package com.cdl.sharding.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:25
 * 描述：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_test_order_Record")
public class TTestOrderRecord {
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
