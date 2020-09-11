package com.yq.canal.thread;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.yq.canal.config.CommonConfig;
import com.yq.canal.service.CanalDealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * job registry instance
 * @author xuxueli 2016-10-02 19:10:24
 */
@Component
@Slf4j
public class CanalConnectorMonitor {

	@Autowired
	public CommonConfig commonConfig;

	@Autowired
	public CanalDealService canalDealService;

	/*public static CanalConnectorMonitor getInstance(String hostName){
		connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostName, 11111), "example", "", "");
		return instance;
	}

	public static CanalConnectorMonitor getInstance(){
		connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
		return instance;
	}*/

	private Thread connectorThread;
	private volatile boolean toStop = false;
	private CanalConnector connector;

	public void start(){
		connectorThread = new Thread(new Runnable() {
			@Override
			public void run() {
				connector = CanalConnectors.newSingleConnector(new InetSocketAddress(commonConfig.getHostName(), commonConfig.getPort()), "example", "", "");
				try {
					connector.connect();
					//监听的表，    格式为数据库.表名,数据库.表名
					connector.subscribe(".*\\..*");
					connector.rollback();
					log.info(">>>>>>>>>>> canal monitor connector thread start hostName: {},port:{}",commonConfig.getHostName(),commonConfig.getPort());
					while (!toStop) {
						Message message = connector.getWithoutAck(commonConfig.getBatchNum()); // 获取指定数量的数据
						long batchId = message.getId();
						if (batchId == -1 || message.getEntries().isEmpty()) {
							Thread.sleep(commonConfig.getSleepTime());
							continue;
						}
						// System.out.println(message.getEntries());
						canalDealService.printEntries(message.getEntries());
						connector.ack(batchId);// 提交确认，消费成功，通知server删除数据
						// connector.rollback(batchId);// 处理失败, 回滚数据，后续重新获取数据
					}
				}catch (Exception e){
					if (!toStop) {
						log.error(">>>>>>>>>>> canal monitor connector thread error:{}", e);
					}
				}finally {
					connector.disconnect();
					log.info(">>>>>>>>>>> canal monitor connector thread stop");
				}
			}
		});
		connectorThread.setDaemon(true);
		connectorThread.setName("CanalConnectorMonitor");
		connectorThread.start();
	}

	public void destroy(){
		if (connector!=null){
			connector.disconnect();
		}
		toStop = true;
		// interrupt and wait
		connectorThread.interrupt();
		try {
			connectorThread.join();
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}



}
