package com.csye6225.demo.repositories;

import com.csye6225.demo.pojos.Task;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
public interface TaskRepository extends CrudRepository<Task, UUID> {
}
