package com.grupo2.kanbanboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.auth.TokenProvider;
import com.grupo2.kanbanboard.entities.User;
import com.grupo2.kanbanboard.requests.AccessTokenInput;
import com.grupo2.kanbanboard.requests.LoginInput;
import com.grupo2.kanbanboard.requests.RegisterInput;
import com.grupo2.kanbanboard.services.AuthService;

import net.eulerframework.common.util.jwt.InvalidJwtException;

@RestController
@RequestMapping("/")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AuthService service;
  @Autowired
  private TokenProvider tokenService;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody RegisterInput data) throws InvalidJwtException {
    service.signUp(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/signin")
  public ResponseEntity<AccessTokenInput> signIn(@RequestBody LoginInput data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var authUser = authenticationManager.authenticate(usernamePassword);
    var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
    return ResponseEntity.ok(new AccessTokenInput(accessToken));
  }
}
