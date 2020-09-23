package com.cdl.sharding.table.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;


/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/23 0023 15:02
 * 描述：解決多数据源驼峰不匹配问题
 */
@Configuration
public class MybatisConfig {

    /*@Qualifier("shardingDataSource")
    ShardingDataSource shardingDataSource;

    @Qualifier("dataSource")
    DataSource dataSource;*/

    /**
     * 使 application.properties配置生效，如果不主动配置，由于@Order配置顺序不同，将导致配置不能及时生效 多数据源配置驼峰法生效
     * @return 数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * 创建会话工厂。
     *
     * @param dataSource 数据源
     * @return 会话工厂
     */

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration config) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
