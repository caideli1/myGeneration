package com.cdl.sharding.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:16
 * 描述：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_test_order_month")
public class TTestOrderMonth {
    /**
     * 提前时间
     */
    @Column(name = "forward_date")
    private LocalDate forwardDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
