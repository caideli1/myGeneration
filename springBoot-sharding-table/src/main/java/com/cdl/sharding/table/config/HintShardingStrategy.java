package com.cdl.sharding.table.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 11:40
 * 描述：Hint分片算法
 */
public class HintShardingStrategy implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        // 获取到强制路由的值
        Collection<Long> values = hintShardingValue.getValues();
        // 下面可以自由发挥，根据value, 返回相应的路由库
        return null;
    }
}
