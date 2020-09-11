package com.yq.canal;

import com.yq.canal.thread.CanalConnectorMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = {"com.yq.canal"})
@MapperScan(basePackages = {"com.yq.canal.mapper"})
@EnableConfigurationProperties
//@EnableCanalClient
public class SpringBootCanalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCanalApplication.class, args);
	}

	/*@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		return new SnowflakeIdWorker(4, 0);
	}*/

}
