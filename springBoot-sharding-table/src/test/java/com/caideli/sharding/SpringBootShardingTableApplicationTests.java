package com.caideli.sharding;

import com.cdl.sharding.table.mapper.TTestUserAmountMapper;
import com.cdl.sharding.table.model.TTestUserAmount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShardingTableApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	TTestUserAmountMapper tTestUserAmountMapper;

	@Test
	public void mapperTest() {
		TTestUserAmount tTestUserAmount = TTestUserAmount.builder()
				.userId(1L)
				.createTime(new Date())
				.build();
		int a = tTestUserAmountMapper.saveTTestUserAmount(tTestUserAmount);
		System.out.println(a);
	}

}
