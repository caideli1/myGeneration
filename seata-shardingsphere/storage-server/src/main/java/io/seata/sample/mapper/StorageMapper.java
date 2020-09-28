package io.seata.sample.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.seata.sample.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

    BigDecimal findById(Long productId);
}
