package com.cdl.sharding.table.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 11:18
 * 描述： 标准分片---精准分片算法 ， 按月分表，通过时间按月分片
 */
@Slf4j
public class OrderMonthPreciseShardingStrategy implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {

        log.info("collection: {} , preciseShardingValue: {}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));

        //return TableUtils.getTable(shardingValue.getLogicTableName(), MonthUtils.getDateShardingValue(shardingValue.getValue()));
        //return "ds0.t_test_order_month_0";
        Calendar cal = Calendar.getInstance();
        cal.setTime(shardingValue.getValue());
        int month = cal.get(Calendar.MONTH) + 1;
        String result = shardingValue.getLogicTableName()+ "_"+ month;
        return result;
    }
}
