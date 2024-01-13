package com.brli.myday.controller;

import com.brli.myday.dto.MeasurementDto;
import com.brli.myday.security.UserDetailsImpl;
import com.brli.myday.service.MeasurementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author brandon
 * 2022-09-26
 */
@RestController
@RequestMapping("/api/measurement")
@SecurityRequirement(name = "bearerAuth")
public class MeasurementController {
  @Autowired
  MeasurementService measurementService;

  @GetMapping("/user")
  ResponseEntity<List<MeasurementDto>> getUserActivities(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    var measurementDtoList = measurementService.getUserMeasurement(userDetails.getId());
    return ResponseEntity.ok(measurementDtoList);
  }



}
