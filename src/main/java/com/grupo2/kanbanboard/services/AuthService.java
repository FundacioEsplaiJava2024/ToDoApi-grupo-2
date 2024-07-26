package com.grupo2.kanbanboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.User;
import com.grupo2.kanbanboard.entities.UserRoleEnum;
import com.grupo2.kanbanboard.repositories.UserRepository;
import com.grupo2.kanbanboard.requests.RegisterInput;

import net.eulerframework.common.util.jwt.InvalidJwtException;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    var user = repository.findByLogin(username);
    return user;
  }

  public UserDetails signUp(RegisterInput data) throws InvalidJwtException {
    if (repository.findByLogin(data.login()) != null) {
      throw new InvalidJwtException("Username already exists");
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.login(), encryptedPassword, UserRoleEnum.USER);
    return repository.save(newUser);
  }
}
