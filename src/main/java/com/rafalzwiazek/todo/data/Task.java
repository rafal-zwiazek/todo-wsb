package com.rafalzwiazek.todo.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Task(String name, Priority priority){
        this.name = name;
        this.priority = priority;
    }
    public static Task taskInit(String name, Priority priority){
        Task task;
        return task = new Task(name, priority);
    }

}
