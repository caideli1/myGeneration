package com.yq.canal.mq.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 22:05  2020/8/20 0020
 * Description :
 */
@Component
public interface CanalSqlToOutStreamClient {

    String TOPIC_INPUT_NAME = "canal-sql-out-topic-input";

    String TOPIC_OUTPUT_NAME = "canal-sql-out-topic-output";

    @Input(TOPIC_INPUT_NAME)
    SubscribableChannel input();

    @Output(TOPIC_OUTPUT_NAME)
    MessageChannel output();


}
