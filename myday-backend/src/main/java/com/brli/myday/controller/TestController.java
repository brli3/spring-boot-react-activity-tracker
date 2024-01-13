package com.brli.myday.controller;

import com.brli.myday.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brandon
 * 2022-09-16
 */
@RestController
@RequestMapping("/api/test")
@SecurityRequirement(name = "bearerAuth")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
//  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  @PreAuthorize("hasRole('USER')")
  public String userAccess(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return  "User content \n" +
            "Current User ID: " + userDetails.getId() + "\n" +
            "Current Username: " + userDetails.getUsername();
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
