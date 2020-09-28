package io.seata.sample;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan({"io.seata.sample.mapper*","com.baomidou.springboot.mapper*"})
@SpringBootApplication(exclude = {
		DruidDataSourceAutoConfigure.class,
		DataSourceAutoConfiguration.class,
		MybatisPlusAutoConfiguration.class
})
public class StorageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageServerApplication.class, args);
	}

}
