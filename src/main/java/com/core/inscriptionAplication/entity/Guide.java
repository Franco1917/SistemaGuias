package com.core.inscriptionAplication.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Guide {

    @Id
    @Column
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private  Teacher teacher;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne()
    @JoinColumn(name = "Student_id")
    User user;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name= "file")
    private String file;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Guide() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Guide(Long id, String title, Teacher teacher, Subject subject, User user, LocalDateTime creationDate, String file) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.subject = subject;
        this.user = user;
        this.creationDate = creationDate;
        this.file = file;
    }
}
