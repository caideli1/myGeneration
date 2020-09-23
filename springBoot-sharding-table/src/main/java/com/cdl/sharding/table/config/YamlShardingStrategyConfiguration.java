package com.cdl.sharding.table.config;

import org.apache.shardingsphere.core.yaml.config.YamlConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.strategy.*;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/22 0022 14:08
 * 描述：通过yaml来配置
 */
public class YamlShardingStrategyConfiguration implements YamlConfiguration {

    // 标准分片策略
    private YamlStandardShardingStrategyConfiguration standard;

    // 复合分片策略
    private YamlComplexShardingStrategyConfiguration complex;

    // hint强制分片策略
    private YamlHintShardingStrategyConfiguration hint;

    // 表达式分片策略
    private YamlInlineShardingStrategyConfiguration inline;

    // 不存在分片策略
    private YamlNoneShardingStrategyConfiguration none;
}
