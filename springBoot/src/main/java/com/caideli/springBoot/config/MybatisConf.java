package com.caideli.springBoot.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 14:48  2019/9/25
 * Description : 注册MyBatis分页插件PageHelper
 */
@Configuration
public class MybatisConf {

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		System.out.println("--------初始化分页参数结束--------");
		return pageHelper;
	}

}
