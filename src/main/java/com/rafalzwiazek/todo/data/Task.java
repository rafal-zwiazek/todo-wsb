package com.rafalzwiazek.todo.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "created", nullable = false)
    LocalDate created;
    @Column (name = "name", nullable = false)
    private String name;
    @Column (name = "description", nullable = false)
    private String description;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    User user;
}
