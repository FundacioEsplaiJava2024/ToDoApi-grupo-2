package com.grupo2.kanbanboard.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.kanbanboard.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
