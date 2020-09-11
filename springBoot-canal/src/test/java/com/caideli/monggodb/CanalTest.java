package com.caideli.monggodb;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 18:31
 * 描述：
 */
public class CanalTest {


    public static void main(String[] args) throws Exception {
//canal.ip = 192.168.56.104
//canal.port = 11111
//canal.destinations = example
//canal.user =
//canal.passwd =
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
        try {
            connector.connect();
            //监听的表，    格式为数据库.表名,数据库.表名
            connector.subscribe(".*\\..*");
            connector.rollback();

            while (true) {
                Message message = connector.getWithoutAck(100); // 获取指定数量的数据
                long batchId = message.getId();
                if (batchId == -1 || message.getEntries().isEmpty()) {
                    Thread.sleep(1000);
                    continue;
                }
                // System.out.println(message.getEntries());
                printEntries(message.getEntries());
                connector.ack(batchId);// 提交确认，消费成功，通知server删除数据
                // connector.rollback(batchId);// 处理失败, 回滚数据，后续重新获取数据
            }
        }catch (Exception e){

        }finally {
            connector.disconnect();
        }
    }

    private static void printEntries(List<Entry> entries) throws Exception {
        for (Entry entry : entries) {
            if (entry.getEntryType() != EntryType.ROWDATA) {
                continue;
            }
            System.out.printf("表名称是："+entry.getHeader().getTableName()+"\n");
            RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());

            EventType eventType = rowChange.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));

            for (RowData rowData : rowChange.getRowDatasList()) {
                switch (rowChange.getEventType()) {
                    case INSERT:
                        System.out.println("INSERT ");
                        printColumns(rowData.getAfterColumnsList());
                        break;
                    case UPDATE:
                        System.out.println("UPDATE ");
                        printColumns(rowData.getAfterColumnsList());
                        break;
                    case DELETE:
                        System.out.println("DELETE ");
                        printColumns(rowData.getBeforeColumnsList());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void printColumns(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }

}
