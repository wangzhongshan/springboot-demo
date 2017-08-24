package killcode.wzs.springbootdemo.service;

import killcode.wzs.springbootdemo.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(Long id);

    List<User> getUsers();

    Map<Long, User> getUsersMap();

    User addUser(User user);

}
