package com.caideli.sharding;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/11/8 13:10
 * 描述：
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cdl.sharding.table.SpringBootShardingTableApplication;
import com.cdl.sharding.table.mapper.TTestOrderMonthMapper;
import com.cdl.sharding.table.mapper.TTestOrderRecordMapper;
import com.cdl.sharding.table.mapper.TTestUserAmountMapper;
import com.cdl.sharding.table.model.TTestOrderMonth;
import com.cdl.sharding.table.model.TTestUserAmount;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
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
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootShardingTableApplication.class)
@Slf4j
public class CommonServicelTest {
    /*private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

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
    }*/

    @Resource
    TTestUserAmountMapper tTestUserAmountMapper;

    @Resource
    TTestOrderMonthMapper tTestOrderMonthMapper;

    @Resource
    TTestOrderRecordMapper tTestOrderRecordMapper;

    @Resource
    Map<String, DataSource> dataSourceMap;

    @Resource
    Map<String, ShardingDataSource> shardingDataSourceMap;

    /**
     * 表达式分片方法测试
     */
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

    /**
     * 多库查询测试，使用
     */
    @Test
    public void mapperSelectTest() {
        List<TTestUserAmount> tTestUserAmountList = tTestUserAmountMapper.selectAll();
        System.out.println(JSONArray.toJSONString(tTestUserAmountList));
    }
}
