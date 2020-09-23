/*
package com.cdl.sharding.table.beifen.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

*/
/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:26
 * 描述：
 *//*

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_test_user_amount")
public class TTestUserAmount {

    */
/**
     * 用户id
     *//*

    @TableId(value = "user_id")
    private Long userId;

    */
/**
     * 创建时间
     *//*

    @TableId(value = "create_time")
    private Date createTime;
}
*/
