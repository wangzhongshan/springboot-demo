package killcode.wzs.springbootdemo.service;

import killcode.wzs.springbootdemo.cache.Cacheable;
import killcode.wzs.springbootdemo.domain.User;
import killcode.wzs.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Override
    // @Cacheable(value = "user")
    @Cacheable(key = "'user:'+#id", expire = 1)
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    // @Cacheable(value = "user",key = "'list'")
    @Cacheable(key = "'userList'", expire = 1)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    // @Cacheable(value = "user",key = "'map'")
    @Cacheable(expire = 1)
    public Map<Long, User> getUsersMap() {
        return userRepository.findAll().stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "user", key = "'list'"),
            @CacheEvict(value = "user", key = "'map'")
    })
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
