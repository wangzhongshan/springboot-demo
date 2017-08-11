package killcode.wzs.springbootdemo;

import killcode.wzs.springbootdemo.domain.User;
import killcode.wzs.springbootdemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUser() {
        // User user = userRepository.findOne(1L);
        // User user = userRepository.findByName("wzs");
        List<User> all = userRepository.findAll();
        System.out.println(all);
        // System.out.println(user);
    }

    @Test
    public void addUser() {
        User user = User.builder().name("cq").age(22).build();
        User user1 = userRepository.save(user);
        System.out.println(user1);
    }
}
