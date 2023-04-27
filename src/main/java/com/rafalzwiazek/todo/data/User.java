package com.rafalzwiazek.todo.data;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Entity
@Table (name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static User userInit(String username, String password){
        return new User(username, password);
    }
}
