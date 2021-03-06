package io.seata.sample;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户服务
 */
@MapperScan({"io.seata.sample.mapper*","com.baomidou.springboot.mapper*"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {
		DruidDataSourceAutoConfigure.class,
		DataSourceAutoConfiguration.class,
		MybatisPlusAutoConfiguration.class
})
public class AccountServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServerApplication.class, args);
	}

}
