package com.caideli.springBoot.service.excel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 交易类型
 * @author bjy
 * @date 2019/2/27 0027 10:08
 */
public enum LoanTypeEnum {
    /**
     * 1：放款申请
     */
    LOANAPPLY(1, "放款申请"),
    /**
     * 2:放款重提
     */
    RELOAN(2, "放款重提"),
    /**
     * 3：正常还款
     */
    NORMALREPAY(3, "正常还款"),
    /**
     * 4：逾期还款
     */
    DUEREPAY(4, "逾期还款"),
    /**
     * 5：提前结清
     */
    ADVANCECLEAR(5, "提前结清"),
    /**
     * 6：展期申请
     */
    EXTENSION(6, "展期申请"),
    /**
     * 7：复贷放款
     */
    REPEATLOAN(7, "复贷放款"),
    /**
     * 待还
     */
    WAITREPAY(8, "待还"),
    /**
     * 9：提前展期
     */
    EXTENSIONAHEAD(9, "提前展期"),
    /**
     * 10：逾期展期
     */
    EXTENSIONOVERDUE(10, "逾期展期");

    private Integer code;

    private String message;

    LoanTypeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(Integer code) {
        Optional<LoanTypeEnum> optional = Stream.of(LoanTypeEnum.values())
                .filter(value -> Objects.equals(code, value.getCode()))
                .findFirst();

        if (optional.isPresent()) {
            return optional.get().getMessage();
        }

        return "未知";
    }

}
