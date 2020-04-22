package com.caideli.springBootEs.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * es自动配置类
 *
 * @author danquan.miao
 * @date 2019/7/29 0029
 * @since 1.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(XpackConfig.class)
public class ElasticSearchAutoConfiguration implements DisposableBean {

    @Autowired
    private XpackConfig xpackConfig;

    private TransportClient transportClient;

    /**
     * 由于使用了ES x-pack插件的认证功能，需要在此处变更访问方式
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(TransportClient.class)
    public TransportClient transportClient() {
        transportClient = new PreBuiltXPackTransportClient(settings());

        xpackConfig.getNodes().forEach(node -> {
            InetSocketTransportAddress inetSocketTransportAddress = null;
            try {
                String[] ip_port = StringUtils.split(node, ":");
                inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(ip_port[0]), Integer.valueOf(ip_port[1]));
            } catch (UnknownHostException e) {
                log.error("启动加载x-pack失败：", e);
            }
            transportClient.addTransportAddresses(inetSocketTransportAddress);
        });

        return transportClient;
    }

    private Settings settings() {
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("cluster.name", xpackConfig.getClusterName());
        if (xpackConfig.isEnable()) {
            return settingsBuilder
                    .put("xpack.security.user", xpackConfig.getAuthCredentials())
                    .build();
        } else {
            return settingsBuilder.build();
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("开始销毁ES的连接");
        if (null != transportClient) {
            transportClient.close();
        }
    }
}
