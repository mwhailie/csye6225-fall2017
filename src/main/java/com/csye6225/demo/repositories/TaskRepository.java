package com.csye6225.demo.repositories;

import com.csye6225.demo.pojos.Task;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.*;

@Transactional
public interface TaskRepository extends CrudRepository<Task, String>{
    public Task findTaskByUser_id(int id);
}
