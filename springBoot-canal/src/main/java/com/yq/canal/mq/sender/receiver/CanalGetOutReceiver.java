package com.yq.canal.mq.sender.receiver;

import com.alibaba.fastjson.JSONObject;
import com.yq.canal.model.dto.SqlDto;
import com.yq.canal.mq.stream.CanalSqlToOutStreamClient;
import com.yq.canal.service.CanalSqlExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 22:05  2020/8/20 0020
 * Description :
 */
@Slf4j
@Component
@EnableBinding(CanalSqlToOutStreamClient.class)
public class CanalGetOutReceiver {

    @Autowired
    private CanalSqlExecuteService canalSqlExecuteService;

    @StreamListener(CanalSqlToOutStreamClient.TOPIC_INPUT_NAME)
    public void receiveMessage(String messageJson) {
        log.info("接收到要消费的sql是：>>>>>>>>>>>>>:\n{}\n",messageJson);
        SqlDto sqlDto = JSONObject.parseObject(messageJson,SqlDto.class);
        try {
            canalSqlExecuteService.execute(sqlDto);
        }catch (Exception e){
            log.error("执行数据同步sql异常,数据：{}",messageJson,e);
        }
    }

}
