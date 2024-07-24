package com.grupo2.kanbanboard.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 15, unique = true)
    private Integer username;

    @Column(nullable = false, unique = true)
    private String email;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
