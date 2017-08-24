package killcode.wzs.springbootdemo.controller;

import killcode.wzs.springbootdemo.domain.User;
import killcode.wzs.springbootdemo.repository.UserRepository;
import killcode.wzs.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User user(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/")
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        userRepository.delete(id);
        return true;
    }

    @GetMapping("/")
    public List<User> users() {
        List<User> users = userService.getUsers();
        Map<Long, User> usersMap = userService.getUsersMap();
        return users;
    }

}
