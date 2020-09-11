/*
package com.yq.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.xpand.starter.canal.annotation.*;
import lombok.extern.slf4j.Slf4j;

*
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 15:36
 * 描述：


@CanalEventListener
@Slf4j
public class BusinessListener {
    //table = {"message_data_hz"}
    @ListenPoint(schema = "zsafood_test")
    public void adUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData
            , CanalEntry.Entry entry, CanalEntry.Header header, CanalEntry.Pair pair, CanalEntry.RowChange rowChange
            , CanalEntry.Type type, Message message) {
        entry.getHeader().getTableName();
        header.getTableName();
        log.info(eventType.getValueDescriptor().getName());
        log.info("数据发生变化！");
        rowData.getBeforeColumnsList().forEach((c) -> System.err.println("更改前数据: " + c.getName() + " :: " + c.getValue()));
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("\n更改后数据: " + c.getName() + " :: " + c.getValue()));
        //rowData.get
    }

 @InsertListenPoint(schema = "zsafood_test")
    public void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        log.info("插入方法！");
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }

    @UpdateListenPoint(schema = "zsafood_test")
    public void onEvent1(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        log.info("修改方法！");
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }

    @DeleteListenPoint(schema = "zsafood_test")
    public void onEvent3(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        log.info("删除方法！");
    }

}
*/
