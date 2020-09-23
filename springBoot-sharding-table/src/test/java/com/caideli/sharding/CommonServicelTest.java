package com.caideli.sharding;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/11/8 13:10
 * 描述：
 */

import com.cdl.sharding.table.SpringBootShardingTableApplication;
import com.cdl.sharding.table.mapper.TTestOrderMonthMapper;
import com.cdl.sharding.table.mapper.TTestOrderRecordMapper;
import com.cdl.sharding.table.mapper.TTestUserAmountMapper;
import com.cdl.sharding.table.model.TTestOrderMonth;
import com.cdl.sharding.table.model.TTestUserAmount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootShardingTableApplication.class)
@Slf4j
public class CommonServicelTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Resource
    TTestUserAmountMapper tTestUserAmountMapper;

    @Resource
    TTestOrderMonthMapper tTestOrderMonthMapper;

    @Resource
    TTestOrderRecordMapper tTestOrderRecordMapper;

    /*@Autowired
    private WalletUtilProcessor walletUtilProcessor;*/

    @Before
    public void setup() throws Exception {
        mvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void test() throws Exception {
        //对象入参和数组入参
//        param("name","第二组").
//                param("userIdList","1","72","20").
        String a = mvc.perform(MockMvcRequestBuilders.post("/mongoSelectByOrderNo").
                accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        System.out.println(a);
    }

    @Test
    public void serviceTest() throws Exception {
        String a = mvc.perform(MockMvcRequestBuilders.post("/mongoSave").
                accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        System.out.println(a);
    }

    @Test
    public void mapperTest() {
        TTestUserAmount tTestUserAmount = TTestUserAmount.builder()
                .userId(4L)
                .createTime(new Date())
                .build();
        tTestUserAmountMapper.saveTTestUserAmount(tTestUserAmount);
    }

    /**
     * 标准分片算法测试
     */
    @Test
    public void mapperSaveStandTest() {
        TTestOrderMonth tTestUserAmount = TTestOrderMonth.builder()
                .forwardDate(LocalDate.now())
                .createTime(new Date())
                .build();
        tTestOrderMonthMapper.insertSelective(tTestUserAmount);
    }

    @Test
    public void mapperSelectTest() {
        TTestUserAmount tTestUserAmount = TTestUserAmount.builder()
                .userId(3L)
                .createTime(new Date())
                .build();
        int a = tTestUserAmountMapper.saveTTestUserAmount(tTestUserAmount);
        System.out.println(a);
    }
}
