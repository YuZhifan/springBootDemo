package com.yzf.springboot.admin.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

		@Value("${spring.redis.hostName}")
	    private  String hostName;
		@Value("${spring.redis.password}")
		private  String password;
	    @Value("${spring.redis.port}")
	    private  int port;
	    @Value("${spring.redis.timeout}")
	    private  int timeout;
	    @Value("${spring.redis.pool.maxIdle}")
	    private int maxIdle;
	    @Value("${spring.redis.pool.maxWait}")
	    private long maxWaitMillis;	
	    @Value("${spring.redis.database}")
	    private int database;
	    @Value("${spring.redis.pool.maxTotal}")
	    private int maxTotal;
	
	@Bean("factory")
	public RedisConnectionFactory redisCF(){
		RedisStandaloneConfiguration rs = new RedisStandaloneConfiguration(hostName,port);
		rs.setDatabase(database);
		rs.setPassword(RedisPassword.of(password));
		JedisPoolConfig jedisPoolCfg = new JedisPoolConfig();
		jedisPoolCfg.setMaxIdle(maxIdle);
		jedisPoolCfg.setMaxTotal(maxTotal);
		jedisPoolCfg.setMaxWaitMillis(maxWaitMillis);
		jedisPoolCfg.setTestOnBorrow(true);
		
		JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(timeout)).usePooling()
				.poolConfig(jedisPoolCfg).build();
		
		JedisConnectionFactory factory = new JedisConnectionFactory(rs,jedisConfig);
		
		return factory; 
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer1 = new Jackson2JsonRedisSerializer(Object.class);
		jackson2JsonRedisSerializer1.setObjectMapper(om);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//		GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer1);
//		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();




//        template.setConnectionFactory(factory);
        //key序列化方式
//        template.setKeySerializer(redisSerializer);
        //value序列化
//        template.setValueSerializer(jackson2JsonRedisSerializer );
        //value hashmap序列化
//        template.setHashValueSerializer(jackson2JsonRedisSerializer );

        return redisTemplate;
    }


	@Bean("stringRedisTemplate")
	public StringRedisTemplate buildRedisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(factory);
		template.setValueSerializer(stringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	@Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = RedisCacheManager.builder(redisCF()).build();
        return cacheManager;
    }
  
}
