package com.csye6225.demo.repositories;

import com.csye6225.demo.pojos.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.*;
@Transactional

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);//666
    public User findByName(String name);//666

}
