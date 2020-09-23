package com.cdl.sharding.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)//,scanBasePackages = {"com.cdl.sharding.table.config"}
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
