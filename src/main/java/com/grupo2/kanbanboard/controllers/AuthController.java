package com.grupo2.kanbanboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.requests.AccessTokenDTO;
import com.grupo2.kanbanboard.requests.LoginInput;
import com.grupo2.kanbanboard.requests.RegisterInput;
import com.grupo2.kanbanboard.services.AuthService;

import net.eulerframework.common.util.jwt.InvalidJwtException;

@RestController
public class AuthController {

  @Autowired
  private AuthService service;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody RegisterInput data) throws InvalidJwtException {
    service.signUp(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signIn(@RequestBody LoginInput data) throws AuthenticationException {
    try {
      var accessToken = service.signIn(data.login(), data.password());
      return ResponseEntity.ok(new AccessTokenDTO(accessToken));
    } catch (AuthenticationException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }
  }
}
