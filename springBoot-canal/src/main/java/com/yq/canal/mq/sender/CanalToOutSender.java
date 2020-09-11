package com.yq.canal.mq.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.yq.canal.model.dto.SqlDto;
import com.yq.canal.mq.stream.CanalSqlToOutStreamClient;
import com.yq.canal.service.CanalSqlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 20:38
 * 描述：
 */
@Slf4j
@Component
@EnableBinding(CanalSqlToOutStreamClient.class)
public class CanalToOutSender {
    @Autowired
    private CanalSqlToOutStreamClient canalSqlToOutStreamClient;

    @Autowired
    private CanalSqlService canalSqlService;

    public void send(String canalSql,String method) {
        if (StringUtils.isNotBlank(canalSql)) {
            String jsonMessage = JSON.toJSONString(SqlDto.builder()
                    .sql(canalSql)
                    .method(method)
                    .build());
            log.info("发送消息>>>>>>>>>>>:\n{}\n", jsonMessage);
            canalSqlToOutStreamClient.output().send(MessageBuilder.withPayload(jsonMessage).build());
        }else {
            log.warn("发送sql的消息为空！");
        }
    }

    public void sendSql(CanalEntry.RowChange rowChange,String tableName){
        //CanalEntry.EventType eventType = rowChange.getEventType();
        for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
            try{
                String method = rowChange.getEventType().getValueDescriptor().getName();
                switch (rowChange.getEventType()) {
                    case INSERT:
                        //log.info("INSERT ");
                        printColumns(rowData.getAfterColumnsList());
                        send(canalSqlService.getInsertSql(rowData.getAfterColumnsList(),tableName),method);
                        break;
                    case UPDATE:
                        //log.info("UPDATE ");
                        printColumns(rowData.getAfterColumnsList());
                        send(canalSqlService.getUpdateSql(rowData.getAfterColumnsList(),tableName),method);
                        break;
                /*case DELETE:
                    System.out.println("DELETE ");
                    printColumns(rowData.getBeforeColumnsList());
                    break;*/
                    default:
                        break;
                }
            }catch (Exception e){
                log.error("发送异常，数据：{}", JSONArray.toJSONString(rowData.getAfterColumnsList()),e);
            }

        }
    }

    public void printColumns(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated() + " isNull=" + column.getIsNull());
        }
    }

}
