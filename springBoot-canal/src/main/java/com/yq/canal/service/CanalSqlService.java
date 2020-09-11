package com.yq.canal.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/20 0020 20:36
 * 描述：
 */
@Service
public class CanalSqlService {

    public String getUpdateSql(List<CanalEntry.Column> columns,String tableName){
        String sql = "update `"+tableName+"` set ";
        List<CanalEntry.Column> columnsNew=null;
        if (CollectionUtils.isNotEmpty(columns)){
            columnsNew = columns.stream().filter(c->c.getUpdated()==true).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(columnsNew)){
            return null;
        }
        for (int i=0; i< columnsNew.size();i++) {
            CanalEntry.Column column = columnsNew.get(i);
            if (i==0){
                sql = sql +"`"+column.getName() + "` = " + column.getValue();
            }else {
                sql = sql + ", `" + column.getName() + "` = " + column.getValue();
            }
        }
        return sql;
    }

    public String getInsertSql(List<CanalEntry.Column> columns,String tableName){
        String sql = "insert `"+tableName+"` (";
        String sqlKeys="";
        String sqlValues=") VALUES(";
        List<CanalEntry.Column> columnsNew=null;
        if (CollectionUtils.isNotEmpty(columns)){
            columnsNew = columns.stream().filter(c->c.getUpdated()==true&&c.getIsNull()==false).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(columnsNew)){
            return null;
        }
        for (int i=0; i< columnsNew.size();i++) {
            CanalEntry.Column column = columnsNew.get(i);
            if (i==0){
                sqlKeys = sqlKeys + "`"+ column.getName() + "`";
                sqlValues = sqlValues + column.getValue();
            }else {
                sqlKeys = sqlKeys + " ,`"+ column.getName() + "`";
                sqlValues = sqlValues + ","+ column.getValue();
            }
        }
        return sql+sqlKeys+sqlValues+")";
    }

}
