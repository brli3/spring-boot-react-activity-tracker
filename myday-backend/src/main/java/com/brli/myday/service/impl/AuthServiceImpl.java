package com.brli.myday.service.impl;

import com.brli.myday.dto.JwtDto;
import com.brli.myday.dto.LoginDto;
import com.brli.myday.dto.RegisterDto;
import com.brli.myday.entity.Role;
import com.brli.myday.entity.User;
import com.brli.myday.enums.RoleEnum;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.exception.UserExistsException;
import com.brli.myday.repository.RoleRepository;
import com.brli.myday.repository.UserRepository;
import com.brli.myday.security.UserDetailsImpl;
import com.brli.myday.service.AuthService;
import com.brli.myday.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author brandon
 * 2022-09-16
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void register(RegisterDto registerDto) {
    if (userRepository.existsByUsername(registerDto.getUsername())) {
      throw new UserExistsException("Username already exists");
    }

    if (userRepository.existsByEmail(registerDto.getEmail())) {
      throw new UserExistsException("Email already exists");
    }

    // Create new user
    var user = User.builder()
            .username(registerDto.getUsername())
            .password(encoder.encode(registerDto.getPassword()))
            .email(registerDto.getEmail())
            .registryTime(LocalDateTime.now())
            .avatar("https://www.pngkey.com/png/full/115-1150152_default-profile-picture-avatar-png-green.png")
            .isActive(true)
            .build();

    // Filter valid roles
    var roleSet = registerDto.getRoles();
    // Default to user role if no roles available
    if (roleSet == null) {
      roleSet = new HashSet<>();
    }

    if (roleSet.isEmpty()) {
      roleSet.add(RoleEnum.ROLE_USER.getName());
    }

    var roleSetFiltered = roleSet.stream()
            .filter(roleName -> roleRepository.existsByName(roleName))
            .collect(Collectors.toSet());

    user.setRoles(String.join(",", roleSetFiltered));
    userRepository.save(user);
    log.info("New registered user: \n" + user.getUsername());
  }

  @Override
  public JwtDto login(LoginDto loginDto) {

    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginDto.getUsername(), loginDto.getPassword()
              )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      return JwtDto.builder()
              .accessToken(jwt)
              .type("Bearer")
              .id(userDetails.getId())
              .username(userDetails.getUsername())
              .email(userDetails.getEmail())
              .roles(roles)
              .build();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
