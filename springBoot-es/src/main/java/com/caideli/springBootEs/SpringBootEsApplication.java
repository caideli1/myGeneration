package com.caideli.springBootEs;

import com.caideli.springBootCommon.config.idconfig.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.caideli.springBootEs","com.caideli.springBootCommon"})
@EnableConfigurationProperties
public class SpringBootEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEsApplication.class, args);
	}

	@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		return new SnowflakeIdWorker(5, 0);
	}

}
