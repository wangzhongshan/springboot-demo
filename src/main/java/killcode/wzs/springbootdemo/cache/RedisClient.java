package killcode.wzs.springbootdemo.cache;

import com.alibaba.fastjson.JSON;
import killcode.wzs.springbootdemo.util.PropertyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

public class RedisClient {
    private static JedisPool pool;
    static {
        Properties props = PropertyUtil.getProperties("application.properties");
        String url = props.getProperty("spring.redis.host", "localhost");
        int port = Integer.parseInt(props.getProperty("spring.redis.port", "6379"));
        pool = new JedisPool(new JedisPoolConfig(), url, port);
    }
    private static RedisClient instance = new RedisClient();

    private RedisClient() {
    }

    public static RedisClient getInstance() {
        return instance;
    }

    public String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    public <T> T get(String key, Class<T> type) {
        try (Jedis jedis = pool.getResource()) {
            String json = jedis.get(key);
            if (json == null) {
                return null;
            }
            return JSON.parseObject(json, type);
        }
    }

    public String set(String key, String value, Duration expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.setex(key, (int) expire.getSeconds(), value);
        }
    }

    public String set(String key, Object value, Duration expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.setex(key, (int) expire.getSeconds(), JSON.toJSONString(value));
        }
    }

    public void delete(String key) {
        try (Jedis jedis = pool.getResource()) {
            jedis.del(key);
        }
    }

    public <R> R execute(Function<Jedis, R> function) {
        try (Jedis jedis = pool.getResource()) {
            return function.apply(jedis);
        }
    }
}
