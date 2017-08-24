package killcode.wzs.springbootdemo;

import killcode.wzs.springbootdemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {


    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisTemplate() {
        User user = User.builder().id(1L).age(11).name("wwwwww").build();
        redisTemplate.opsForValue().set("wzs-user1", user, 1, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set("wzs-user2", "wzs", 1, TimeUnit.MINUTES);
        User user1 = redisTemplate.opsForValue().get("wzs-user1");
        System.out.println(user1);
        String user2 = stringRedisTemplate.opsForValue().get("wzs-user2");
        System.out.println(user2);
    }
}
