package com.brli.myday.service;

import com.brli.myday.dto.JwtDto;
import com.brli.myday.dto.LoginDto;
import com.brli.myday.dto.RegisterDto;

/**
 * @author brandon
 * 2022-09-16
 */
public interface AuthService {

  void register(RegisterDto registerDto);

  JwtDto login(LoginDto loginDto);

}
