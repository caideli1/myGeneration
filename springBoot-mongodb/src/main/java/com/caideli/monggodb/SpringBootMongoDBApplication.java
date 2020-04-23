package com.caideli.monggodb;

import com.caideli.springBootCommon.config.idconfig.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.caideli.monggodb","com.caideli.springBootCommon"})
@EnableConfigurationProperties
public class SpringBootMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDBApplication.class, args);
	}

	@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		return new SnowflakeIdWorker(4, 0);
	}

}
