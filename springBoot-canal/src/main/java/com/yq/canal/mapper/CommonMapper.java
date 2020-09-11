package com.yq.canal.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/8/21 0021 15:18
 * 描述：
 */
@Repository
public interface CommonMapper {

    /**
     * 默认执行 update语句
     * @param executeSql
     * @return
     */
    int executeUpdate(@Param("executeSql") String executeSql );

    /**
     * 默认执行 insert语句
     * @param executeSql
     * @return
     */
    int executeInsert(@Param("executeSql") String executeSql );

}
