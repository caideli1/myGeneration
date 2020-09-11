package com.yq.canal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/21 0021 16:00
 * 描述：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlDto {
    /**
     * 执行的sql
     */
    private String sql;

    /**
     * 执行的方法
     */
    private String method;
}
