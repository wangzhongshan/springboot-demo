package killcode.wzs.springbootdemo.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import killcode.wzs.springbootdemo.cache.CacheUtil;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        //使用fastjson
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        // redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);//设置默认的Serialize，包含 keySerializer & valueSerializer
        // redisTemplate.setKeySerializer(fastJsonRedisSerializer);//单独设置keySerializer
        redisTemplate.setKeySerializer(new GenericToStringSerializer(Object.class));
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);//单独设置valueSerializer

        //使用Jackson
        // redisTemplate.setKeySerializer(new GenericToStringSerializer(Object.class));
        // redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        rcm.setUsePrefix(true);
        //设置默认缓存过期时间
        rcm.setDefaultExpiration(300);//秒
        //单独设置缓存时间
        Map<String, Long> cacheExpires = new HashMap<>();
        cacheExpires.put("users", 600L);//秒
        //...自定义缓存时间
        rcm.setExpires(cacheExpires);
        return rcm;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return CacheUtil::genCacheKey;
    }
}
