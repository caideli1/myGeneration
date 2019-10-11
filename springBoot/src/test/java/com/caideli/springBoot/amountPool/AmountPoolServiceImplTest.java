package com.caideli.springBoot.amountPool;

import com.alibaba.fastjson.JSON;
import com.caideli.springBoot.Application;
import com.caideli.springBoot.ApplicationTest;
import com.caideli.springBoot.model.AmountPool;
import com.caideli.springBoot.service.AmountPoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/24 11:06
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AmountPoolServiceImplTest {
    @Autowired
    private AmountPoolService amountPoolService;
    /*@Autowired
    private ThreadPoolTaskExecutor taskExecutor;*/
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void insert() {
        int result = 0;
        for (int i=1;i<=22;i++){
            AmountPool amountPool = new AmountPool();
            amountPool.setAmount(new BigDecimal(i*10000));
            amountPool.setOriginalAmount(new BigDecimal(i*9000));
            amountPool.setStatus(i%2);
            amountPool.setUsedAmount(new BigDecimal(i*8000));
            amountPool.setUpdateTime(new Date());
            result = result + amountPoolService.insertSelective(amountPool);
        }
        System.out.println(result);
    }

    @Test
    public void update() {
        AmountPool amountPool = new AmountPool();
        amountPool.setId(1);
        amountPool.setAmount(new BigDecimal(11111));
        amountPool.setOriginalAmount(new BigDecimal(1111));
        amountPool.setStatus(0);
        amountPool.setUsedAmount(new BigDecimal(2222));
        amountPool.setUpdateTime(new Date());
        amountPoolService.updateByPrimaryKeySelective(amountPool);
    }

    @Test
    public void queryParamsByType() {
        AmountPool amountPool = new AmountPool();
        amountPool.setStatus(0);
        System.out.println(JSON.toJSONString(amountPoolService.selectPage(amountPool,1,5)));
    }

    @Test
    public void selectOne() {
        AmountPool amountPool = new AmountPool();
        amountPool.setStatus(0);
        amountPool.setOriginalAmount(new BigDecimal(1111));
        System.out.println(JSON.toJSONString(amountPoolService.selectOne(amountPool)));
    }

    @Test
    public void queryParamsByOrderBy() {
        AmountPool amountPool = new AmountPool();
        amountPool.setStatus(0);
        System.out.println(JSON.toJSONString(amountPoolService.selectPage(amountPool,1,5)));
    }

    @Test
    public void delete() {
        amountPoolService.deleteByPrimaryKey(22);
    }

    @Test
    public void ThreadUpdate() {
        List<Thread> threadList = new ArrayList<>();
        //一共20000，模拟12个人同时消费，每次消费4000，扣除1000的风险，4次扣除结束，第五次被限制
        for (int i=1;i<=12;i++){
            UserThreadConsumer userThreadConsumer = new UserThreadConsumer(amountPoolService,20,new BigDecimal(-4000),new BigDecimal(1000),i);
            Thread thread = new Thread(userThreadConsumer,"第"+i+"个人：");
//            threadList.add(thread);
            thread.start();
        }
    }
    @Test
    public void ThreadUpdateExecute() {
        for (int i=1;i<=12;i++){
            UserThreadConsumer userThreadConsumer = new UserThreadConsumer(amountPoolService,20,new BigDecimal(-4000),new BigDecimal(1000),i);
            threadPoolExecutor.execute(userThreadConsumer);
        }
    }
}
