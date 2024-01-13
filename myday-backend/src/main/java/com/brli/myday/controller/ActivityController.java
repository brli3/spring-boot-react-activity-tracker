package com.brli.myday.controller;

import com.brli.myday.dto.ActivityCreateDto;
import com.brli.myday.dto.ActivityDto;
import com.brli.myday.dto.ActivityUpdateDto;
import com.brli.myday.entity.Activity;
import com.brli.myday.security.UserDetailsImpl;
import com.brli.myday.service.ActivityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
@RestController
@RequestMapping("/api/activity")
@SecurityRequirement(name = "bearerAuth")
public class ActivityController {
  @Autowired
  ActivityService activityService;

  @PostMapping("/create")
  ResponseEntity<Activity> createActivity(@RequestBody ActivityCreateDto activityCreateDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
    var userId = userDetails.getId();
    var activity = activityService.createActivity(activityCreateDto, userId);
    return ResponseEntity.ok(activity);
  }

  @PutMapping("/update")
  ResponseEntity<Activity> updateActivity(@RequestBody ActivityUpdateDto activityUpdateDto) {
    var activity = activityService.updateActivity(activityUpdateDto);
    return ResponseEntity.ok(activity);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteActivity(@PathVariable Long id) {
    activityService.deleteActivity(id);
    return ResponseEntity.ok("Activity deleted");
  }

  @GetMapping("/user")
  ResponseEntity<List<ActivityDto>> getUserActivities(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    var activityDtoList = activityService.getUserActivities(userDetails.getId());
    return ResponseEntity.ok(activityDtoList);
  }

  @GetMapping("/all")
  ResponseEntity<List<ActivityDto>> getAllActivities() {
    var activityDtoList = activityService.getActivities();
    return ResponseEntity.ok(activityDtoList);
  }

}
