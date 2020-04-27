package com.caideli.springBoot.service.excel;


import java.util.Optional;
import java.util.stream.Stream;

/**
 * 报表类型枚举
 *
 * @author danquan.miao
 * @date 2019/5/5 0005
 * @since 1.0.0
 */
public enum ReportTypeEnum implements EnumKeyValue<ReportTypeEnum> {
    LOAN_STATISTICS(1, "放款汇总"),
    LOAN_RECORD(2, "放款记录"),
    LOAN_FAIL(3, "放款失败"),
    UNREPAY_STATISTICS(4, "待收汇总"),
    UNREPAY_RECORD(5, "待还记录"),
    REPAYED_RECORD(6, "已还记录"),
    REPAY_FAIL(7, "还款失败"),
    TRANSACTION_RECORD(8, "交易记录"),
    OVER_DUE_ORDER(9, "逾期订单"),
    //author by zhujingtao 添加 报表导出 数据项
    COLLECTION_DATA_ORDER(10, "催收财务数据"),
    COLLECTION_MAN_ORDER(11, "催收员数据"),
    ;

    private Integer code;

    private String message;

    ReportTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getByCode(Integer code) {
        String message = "导出报表";
        Optional<ReportTypeEnum> optional = Stream.of(ReportTypeEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst();
        if (optional.isPresent()) {
            message = optional.get().getMessage();
        }

        return message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
