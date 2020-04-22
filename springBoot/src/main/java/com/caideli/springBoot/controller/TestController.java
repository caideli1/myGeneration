package com.caideli.springBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.caideli.springBoot.base.JsonResult;
import com.caideli.springBoot.config.idconfig.SnowflakeIdWorker;
import com.caideli.springBoot.service.AmountPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/23 20:11
 * 描述：
 */
@RestController
public class TestController {
    @Autowired
    private AmountPoolService amountPoolService;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/getJsonStr")
    public JSONObject getJsonStr() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","Hello World!");
        return jsonObject;
    }

    @RequestMapping("/threadUpdateExecute")
    public JsonResult ThreadUpdateExecute() {
        for (int i=1;i<=12;i++){
            UserThreadConsumer userThreadConsumer = new UserThreadConsumer(amountPoolService,20,new BigDecimal(-4000),new BigDecimal(1000),i);
            threadPoolExecutor.execute(userThreadConsumer);
        }
        return JsonResult.ok();
    }





}
