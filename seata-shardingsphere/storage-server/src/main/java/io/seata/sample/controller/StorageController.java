package io.seata.sample.controller;


import io.seata.core.context.RootContext;
import io.seata.sample.entity.Storage;
import io.seata.sample.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageServiceImpl;

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @RequestMapping("decrease")
    public String decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count){
        log.info("XID为：{}", RootContext.getXID());
        storageServiceImpl.decrease(productId,count);
        log.info("XID为：{}", RootContext.getXID());
        return "Decrease storage success";
    }

    /*@GetMapping(value = "price")
    public BigDecimal findById(@RequestParam("productId") Long productId) {
        return storageServiceImpl.findById(productId);
    }*/
}
