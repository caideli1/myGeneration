package com.cdl.sharding.table.config;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 11:30
 * 描述：复合分片算法
 */
public class OrderRecordComplexShardingStrategy implements ComplexKeysShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        Integer size = availableTargetNames.size();
        // 获取表
        return getTablesOne(complexKeysShardingValue, size);
    }

    /**
     * 返回多个，就会插入多条
     * @param complexKeysShardingValue
     * @param size
     * @return
     */
    private Set<String> getTables(ComplexKeysShardingValue<Long> complexKeysShardingValue, Integer size) {
        Set<String> tables = new HashSet<>();
        // 分页条件 , 包含了多个分片键的值
        Map<String, Collection<Long>> map = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        map.forEach((k, v) -> {
            // 获取Value
            v.forEach(value -> {
                //tables.add(TableUtils.getTable(complexKeysShardingValue.getLogicTableName(), String.valueOf(value % size)));
                tables.add(complexKeysShardingValue.getLogicTableName()+"_"+ (value % size));
            });

        });
        return tables;
    }

    /**
     * 返回一个，插入一条
     * @param complexKeysShardingValue
     * @param size
     * @return
     */
    private Set<String> getTablesOne(ComplexKeysShardingValue<Long> complexKeysShardingValue, Integer size) {
        Set<String> tables = new HashSet<>();
        // 分页条件 , 包含了多个分片键的值
        Map<String, Collection<Long>> map = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        AtomicReference<Long> sum= new AtomicReference<>(0L);

        map.forEach((k, v) -> {
            // 获取Value
            v.forEach(value -> {
                sum.set(sum.get() + value);
            });

        });
        tables.add(complexKeysShardingValue.getLogicTableName()+"_"+ (sum.get() % size));
        return tables;
    }
}
