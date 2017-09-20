package com.csye6225.demo.repositories;

import com.csye6225.demo.pojos.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
