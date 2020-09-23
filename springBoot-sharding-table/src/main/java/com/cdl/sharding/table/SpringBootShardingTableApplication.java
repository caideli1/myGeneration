package com.cdl.sharding.table;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication//(scanBasePackages = {"com.cdl.sharding.table.config"})
@MapperScan(basePackages = {"com.cdl.sharding.table.mapper"})
//@EnableConfigurationProperties
public class SpringBootShardingTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShardingTableApplication.class, args);
	}

	/*@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		return new SnowflakeIdWorker(4, 0);
	}*/

}
