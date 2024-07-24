package com.grupo2.kanbanboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.grupo2.kanbanboard.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
  }
