package org.wzs.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wzs.springbootdemo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
