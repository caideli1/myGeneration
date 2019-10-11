package com.caideli.springBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/25 14:06
 * 描述：
 */
@Configuration
public class ThreadPoolExecutorConfig {
    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor  = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
        System.out.println("--------初始化线程池结束--------");
        return threadPoolExecutor;
    }

}
