package com.caideli.monggodb;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/11/8 13:10
 * 描述：
 */

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootMongoDBApplication.class)
@Slf4j
public class CommonServicelTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

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
}
