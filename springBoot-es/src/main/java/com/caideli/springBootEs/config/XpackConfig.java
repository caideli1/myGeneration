package com.caideli.springBootEs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ES x-pack 配置
 *
 * @author walle
 */
@Data
@Component
@ConfigurationProperties(prefix = "x-pack.elasticsearch")
class XpackConfig {

    private boolean enable;

    private String clusterName;

    private String authCredentials;

    private List<String> nodes;
}
