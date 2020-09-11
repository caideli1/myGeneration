package com.yq.canal.service;

import com.yq.canal.enums.MethodEnum;
import com.yq.canal.mapper.CommonMapper;
import com.yq.canal.model.dto.SqlDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/21 0021 15:41
 * 描述：
 */
@Slf4j
@Service
public class CanalSqlExecuteService {

    @Autowired
    public CommonMapper commonMapper;

    /**
     * 执行sql
     * @param sqlDto
     */
    public void  execute(SqlDto sqlDto){
        if (MethodEnum.UPDATE.getValue().equals(sqlDto.getMethod())){
            commonMapper.executeUpdate(sqlDto.getSql());
        }
        else if (MethodEnum.INSERT.getValue().equals(sqlDto.getMethod())){
            commonMapper.executeInsert(sqlDto.getSql());
        }else {
            log.error("no execute method :: {} sql::{}",sqlDto.getMethod(),sqlDto.getSql());
        }
    }

}
