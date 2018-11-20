package com.yzf.springboot.service;

import com.yzf.springboot.service.HiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiServiceTest {

	@Autowired
	private HiService hiService;

	@Test
	public void contextLoads() {
		System.out.println(hiService.sayHi());
	}

}
