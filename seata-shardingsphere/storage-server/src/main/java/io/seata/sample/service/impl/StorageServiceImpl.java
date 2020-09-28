package io.seata.sample.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.sample.entity.Storage;
import io.seata.sample.mapper.StorageMapper;
import io.seata.sample.service.StorageService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("storageServiceImpl")
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        String xid = RootContext.getXID();
        LOGGER.info("------->扣减库存开始,xid:{}", xid);
        storageMapper.decrease(productId,count);
        LOGGER.info("------->扣减库存结束");
    }

    //@Override
    //public BigDecimal findById(Long productId) {
       // return storageMapper.findById(productId);
    //}
}
