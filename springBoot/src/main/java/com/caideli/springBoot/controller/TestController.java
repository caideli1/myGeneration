package com.caideli.springBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.caideli.springBoot.base.JsonResult;
import com.caideli.springBoot.config.idconfig.SnowflakeIdWorker;
import com.caideli.springBoot.model.elasticsearch.RiskAddressBook;
import com.caideli.springBoot.service.AmountPoolService;
import com.caideli.springBoot.service.elasticsearch.RiskAddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private RiskAddressBookRepository addressBookRepository;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

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

    @RequestMapping("/esSave")
    public JsonResult esSave() {
        List<RiskAddressBook> riskAddressBookList = new ArrayList<>();
        for (int i=1;i<=12;i++){
            RiskAddressBook riskAddressBook = new RiskAddressBook();
            riskAddressBook.setId(snowflakeIdWorker.nextId());
            riskAddressBook.setOrderNo(Long.parseLong(String.valueOf(i%3)));
            riskAddressBook.setRegion("");
            riskAddressBook.setLastContactTime(null);
            riskAddressBook.setRelation(0L);
            riskAddressBook.setContactCount(0L);
            riskAddressBook.setPhone(i+i+"");
            riskAddressBook.setName("我是第"+i+"名字");
            riskAddressBook.setRepeatCount(0L);
            riskAddressBook.setCreateTime(new Date());
            riskAddressBook.setUpdateTime(new Date());
            riskAddressBookList.add(riskAddressBook);
        }

        return JsonResult.ok(addressBookRepository.saveAll(riskAddressBookList));
    }

    @RequestMapping("/esSelect")
    public JsonResult esSelect() {
        return JsonResult.ok(addressBookRepository.findAllByOrderNo(1L, PageRequest.of(0,1000)));
    }





}
