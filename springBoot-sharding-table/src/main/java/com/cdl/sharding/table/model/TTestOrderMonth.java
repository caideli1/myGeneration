package com.cdl.sharding.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class TTestOrderMonth {
    /**
     * 提前时间
     */
    private LocalDate forwardDate;

    /**
     * 创建时间
     */
    private Date createTime;
}
