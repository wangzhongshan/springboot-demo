package killcode.wzs.springbootdemo.controller;

import killcode.wzs.springbootdemo.domain.User;
import killcode.wzs.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User user(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @PostMapping("")
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        userRepository.delete(id);
        return true;
    }

}
