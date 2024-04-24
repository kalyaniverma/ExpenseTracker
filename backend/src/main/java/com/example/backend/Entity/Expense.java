package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String category;

    @Column
    private LocalDate date;

    @Column(nullable = false)
    private int amount;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
