package com.caideli.monggodb.controller;

import com.alibaba.fastjson.JSONObject;
import com.caideli.monggodb.model.RiskAddressBook;
import com.caideli.monggodb.service.RiskAddressBookRepository;
import com.caideli.springBootCommon.base.JsonResult;
import com.caideli.springBootCommon.config.idconfig.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/23 20:11
 * 描述：
 */
@RestController
public class TestController {
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

    @RequestMapping("/mongoSave")
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

    @RequestMapping("/mongoSelectByOrderNo")
    public JsonResult esSelectByOrderNo() {
        return JsonResult.ok(addressBookRepository.findAllByOrderNo(1L, PageRequest.of(0,10)));
    }

    @RequestMapping("/mongoSelectAll")
    public JsonResult esSelectAll() {
        return JsonResult.ok(addressBookRepository.findAll(PageRequest.of(0,10)));
    }





}
