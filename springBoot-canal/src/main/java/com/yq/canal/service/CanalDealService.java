package com.yq.canal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.yq.canal.config.CommonConfig;
import com.yq.canal.mq.sender.CanalToOutSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.otter.canal.protocol.CanalEntry.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 20:07
 * 描述：
 */
@Service
@Slf4j
public class CanalDealService {

    @Autowired
    private CanalToOutSender canalToOutSender;
    @Autowired
    private CommonConfig commonConfig;

    public void printEntries(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            try {
                if (entry.getEntryType() != EntryType.ROWDATA) {
                    continue;
                }
                String tableName = entry.getHeader().getTableName();
                String databaseName = entry.getHeader().getSchemaName();

                String tableNameList = commonConfig.getTableNames();
                String databaseNameList = commonConfig.getDatabaseNames();
                if (StringUtils.isEmpty(tableNameList)){
                    log.error("同步的表名没有配置！");
                }
                if (StringUtils.isEmpty(databaseNameList)){
                    log.error("同步的数据库没有配置！");
                }

                if (!Arrays.asList(tableNameList.split(",")).contains(tableName)){
                    log.warn("不属于需要同步的表！{}",tableName);
                    continue;
                }
                if (!Arrays.asList(databaseNameList.split(",")).contains(databaseName)){
                    log.warn("不属于需要同步的数据库！{}",databaseName);
                    continue;
                }
                RowChange rowChange  = RowChange.parseFrom(entry.getStoreValue());

                log.info(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                        entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                        entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), rowChange.getEventType()));
                canalToOutSender.sendSql(rowChange,tableName);

            }catch (Exception e){
                log.error("同步异常",e);
            }
        }
    }
}
