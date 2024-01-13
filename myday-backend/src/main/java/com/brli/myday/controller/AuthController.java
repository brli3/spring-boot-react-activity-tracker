package com.brli.myday.controller;

import com.brli.myday.dto.LoginDto;
import com.brli.myday.dto.RegisterDto;
import com.brli.myday.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author brandon
 * 2022-09-16
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthService authService;

  @PostMapping("/register")
  ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
    authService.register(registerDto);
    return ResponseEntity.ok("New user registered");
  }

  @PostMapping("/login")
  ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
    var jwtDto = authService.login(loginDto);
    if (jwtDto != null) return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    return new ResponseEntity<>("Login failed: wrong username or password",
            HttpStatus.NOT_FOUND);
  }
}
