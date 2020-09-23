package com.cdl.sharding.table.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 11:18
 * 描述： 标准分片---精准分片算法 ， 按月分表，通过时间按月分片
 */
@Slf4j
public class OrderMonthPreciseShardingStrategy implements PreciseShardingAlgorithm<LocalDate> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDate> shardingValue) {

        log.info("collection: {} , preciseShardingValue: {}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));

        //return TableUtils.getTable(shardingValue.getLogicTableName(), MonthUtils.getDateShardingValue(shardingValue.getValue()));
        //return "ds0.t_test_order_month_0";
        String result = shardingValue.getLogicTableName()+ shardingValue.getValue().getDayOfMonth();
        return result;
    }
}
