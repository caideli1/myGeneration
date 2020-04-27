package com.caideli.springBoot.service.excel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 处理对象枚举
 *
 * @author danquan.miao
 * @date 2019/6/5 0005
 * @since 1.0.0
 */
public enum HandlerTypeEnum {
    PLATFORM(1, "平台"),
    USER(2, "用户"),
    MANUAL(3, "人工"),
    ;

    HandlerTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    };

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(Integer code) {
        Optional<HandlerTypeEnum> optional = Stream.of(HandlerTypeEnum.values())
                .filter(value -> Objects.equals(code, value.getCode()))
                .findFirst();

        if (optional.isPresent()) {
            return optional.get().getMessage();
        }

        return "未知";
    }
}
