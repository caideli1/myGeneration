package com.caideli.springBoot.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 *
 * @author danquan.miao
 * @date  2019/4/30 0030
 * @since 1.0.0
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
