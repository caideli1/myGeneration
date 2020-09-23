package com.cdl.sharding.table.mapper;

import com.cdl.sharding.table.model.TTestUserAmount;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:36
 * 描述：
 */
@Repository
public interface TTestUserAmountMapper extends Mapper<TTestUserAmount> {
    int saveTTestUserAmount(TTestUserAmount tTestUserAmount);
}
