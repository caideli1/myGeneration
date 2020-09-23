/*
package com.cdl.sharding.table.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

*/
/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 19:04
 * 描述：
 *//*

@Component
//@RefreshScope
@Data
public class CommonConfig {
    */
/**
     *  地址
     *//*

    @Value("${canal.server.hostname}")
    private String hostName;

    */
/**
     * 端口
     *//*

    @Value("${canal.server.port}")
    private int port;

    */
/* 重试睡眠时间 *//*

    @Value("${connect.sleep.time}")
    private long sleepTime;

    */
/**
     * 每次获取的数量
     *//*

    @Value("${canal.batch.num}")
    private int batchNum;

    @Value("${canal.tablenames}")
    private String tableNames;

    @Value("${canal.databasenames}")
    private String databaseNames;
}
*/
