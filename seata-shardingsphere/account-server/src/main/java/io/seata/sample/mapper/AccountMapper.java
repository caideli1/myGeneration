package io.seata.sample.mapper;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.seata.sample.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);


    BigDecimal findByUserId(Long userId);
}
