package com.csye6225.demo.pojos;

import org.hibernate.annotations.GenericGenerator;
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

    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}