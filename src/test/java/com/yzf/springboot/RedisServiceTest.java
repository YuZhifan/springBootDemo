package com.yzf.springboot;

import com.yzf.springboot.util.MySpringUtil;
import com.yzf.springboot.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Test
    public void TestRedis() {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.set("name", "zhangsan");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    public void TestRedis1() {
        RedisUtil redisUtil = new RedisUtil();
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        redisUtil.getInstance().opsForHash().put("key", "field", map);
        redisUtil.getInstance().opsForHash().put("key", "field2", map);
        redisUtil.getInstance().opsForHash().put("key", "field3", map);
        Map obj = (Map) redisUtil.getInstance().boundHashOps("key").get("field");
        System.out.println(map.get("key"));
    }

    @Test
    public void TestRedisDelete() {
        String key = "USER_yuzhifan";
        System.out.println(RedisUtil.delete(key));
    }

    @Test
    public void TestRedisBean() {
        ApplicationContext context = MySpringUtil.getApplicationContext();
        RedisTemplate redisTemplate = MySpringUtil.getBean("redisTemplate", RedisTemplate.class);
        RedisTemplate StringRedisTemplate = MySpringUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
    }

}
