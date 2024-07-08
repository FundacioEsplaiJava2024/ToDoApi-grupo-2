package com.grupo2.kanbanboard.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    
    @Column(length = 16, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(20) not null default 'PENDING'")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @Column
    private String project_id;
}
