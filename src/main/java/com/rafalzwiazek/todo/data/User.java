package com.rafalzwiazek.todo.data;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Entity
@Table (name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "credentials", nullable = false)
    private String credentials;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true)
    List<Task> taskList;

}
