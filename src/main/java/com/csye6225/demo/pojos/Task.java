package com.csye6225.demo.pojos;

import com.csye6225.demo.repositories.UserRepository;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    @Size(max = 4096)
    private String description;
    @ManyToOne(cascade=CascadeType.ALL)
    private long userId;


    @OneToMany
    private List<Attachment> attachmentList;

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
