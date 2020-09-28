package io.seata.sample.controller;

import io.seata.sample.service.OrderService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    /**
     * 创建订单
     * @return
     */
    @GetMapping("create")
    public String create(
            @RequestParam("productId") Long productId,
            @RequestParam("userId") Long userId,
            @RequestParam("buyCount") int count
    ){
        orderServiceImpl.create(productId,userId,count);
        return "订单创建成功";
    }

    /**
     * 修改订单状态
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping("update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        orderServiceImpl.update(userId,money,status);
        return "订单状态修改成功";
    }
}
