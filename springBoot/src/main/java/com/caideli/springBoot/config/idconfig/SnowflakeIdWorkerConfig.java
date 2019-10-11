package com.caideli.springBoot.config.idconfig;

import com.caideli.springBoot.config.idconfig.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/26 10:28
 * 描述：
 */
@Configuration
public class SnowflakeIdWorkerConfig {
    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(0, 0);
    }
}
