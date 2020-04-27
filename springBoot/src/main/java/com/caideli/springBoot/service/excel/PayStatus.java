package com.caideli.springBoot.service.excel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 支付状态
 * @author bjy
 * @date 2019/2/26 0026  16:36
 */

public enum PayStatus {
	//-1：初始化
	INITIALIZE(-1, "初始化"),
    //0：支付失败
    FAILURE(0, "支付失败"),
    //1：支付成功
    SUCCESS(1, "支付成功"),
    //2: 支付中
    UNDERWAY(2, "支付中"),
    //3：等待支付
    WAIT(3, "等待支付"),

    BANKCARD_VALIDATING(10,"银行卡校验中"),

    WAIT_IN_KUDOS(11, "kudos放款池中等待")
    ;


	
    public int num;

    private String message;
    
    PayStatus(int num, String message){
        this.num = num;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getMessageByNum(Integer num) {
        Optional<PayStatus> optional = Stream.of(PayStatus.values())
                .filter(value -> Objects.equals(num, value.num))
                .findFirst();

        if (optional.isPresent()) {
            return optional.get().getMessage();
        }

        return "未知";
    }

}
