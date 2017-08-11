package killcode.wzs.springbootdemo.repository;

import killcode.wzs.springbootdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
