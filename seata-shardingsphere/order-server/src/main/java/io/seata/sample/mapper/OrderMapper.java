package io.seata.sample.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.seata.sample.entity.Order;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 创建订单
     * @param order
     * @return
     */
    void create(Order order);

    /**
     * 修改订单金额
     * @param userId
     * @param money
     */
    void update(@Param("userId") Long userId,@Param("money") BigDecimal money, @Param("status") Integer status);
}
