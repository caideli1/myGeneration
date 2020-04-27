package com.caideli.springBoot.service.excel;

/**
 * 结果转名字枚举
 *
 * @author danquan.miao
 * @date 2019/6/5 0005
 * @since 1.0.0
 */
public enum ResultConvertNameEnum {
    /**
     * 支付状态
     */
    PAY_STATUS("payStatus"),
    /**
     * 订单状态
     */
    ORDER_STATUS("orderStatus"),

    /**
     * 訂單編號
     */
    ORDER_NUM("orderNum"),
    ORDER_NO("orderNo"),
    /**
     * 处理对象
     */
    HANDLER_TYPE("handler"),
    /**
     * 交易类型
     */
    LOAN_TYPE("loanType"),
    /**
     * 借据状态
     */
    LOAN_STATUS("loanStatus"),
    ;
    ResultConvertNameEnum(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
