package com.caideli.springBoot.service.excel;

/**
 * @author walle
 */
public interface EnumKeyValue<E extends Enum> {

    Integer getCode();

    String getMessage();
}
