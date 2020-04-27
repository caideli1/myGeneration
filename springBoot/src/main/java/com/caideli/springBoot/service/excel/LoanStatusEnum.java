package com.caideli.springBoot.service.excel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 借据状态枚举
 *
 * @author danquan.miao
 * @date 2019/6/5 0005
 * @since 1.0.0
 */
public enum LoanStatusEnum {
    NORMAL(0, "正常"),
    OVER_DUE(1, "逾期"),
    FINISH(2, "完成"),
    UN_ACTIVE(3, "未激活"),
    HANDLED(4, "已处置"),
    // TODO 有个定义到常量中的需要下个版本处理
    IS_INVALID(5, "已失效"),
    ;

    LoanStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(Integer code) {
        Optional<LoanStatusEnum> optional = Stream.of(LoanStatusEnum.values())
                .filter(value -> Objects.equals(code, value.getCode()))
                .findFirst();

        if (optional.isPresent()) {
            return optional.get().getMessage();
        }

        return "未知";
    }

}
