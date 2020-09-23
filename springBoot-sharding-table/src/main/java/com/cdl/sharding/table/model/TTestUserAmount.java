package com.cdl.sharding.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:26
 * 描述：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TTestUserAmount {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;
}
