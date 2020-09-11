package com.yq.canal.enums;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/21 0021 17:10
 * 描述：
 */
public enum MethodEnum {
    UPDATE("update", "UPDATE"),
    INSERT("insert", "INSERT"),
    ;

    MethodEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }}
