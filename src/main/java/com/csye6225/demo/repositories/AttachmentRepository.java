package com.csye6225.demo.repositories;

import com.csye6225.demo.pojos.Attachment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.*;
@Transactional
public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {

}
