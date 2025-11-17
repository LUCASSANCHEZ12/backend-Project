package com.backend.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(
        name = "todos"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private boolean completed = false;

    @Column(name = "created_time")
    private LocalTime createdTime;

    @Column(name = "finished_time")
    private LocalTime finishedTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
