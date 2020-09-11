package com.yq.canal.listener;

import com.yq.canal.thread.CanalConnectorMonitor;
import org.springframework.util.Assert;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 20:19
 * 描述：
 */
public class CanalDynamicStart {

    private CanalConnectorMonitor canalConnectorMonitor;

    public CanalDynamicStart (CanalConnectorMonitor canalConnectorMonitor){
        this.canalConnectorMonitor = canalConnectorMonitor;
    }

    public void start() throws Exception {
        // monitor run
        canalConnectorMonitor.start();
    }


    public void destroy() throws Exception {
        // stop
        canalConnectorMonitor.destroy();
    }
}
