package com.brli.myday.service;

import com.brli.myday.dto.ActivityCreateDto;
import com.brli.myday.dto.ActivityDto;
import com.brli.myday.dto.ActivityUpdateDto;
import com.brli.myday.entity.Activity;

import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
public interface ActivityService {

  Activity createActivity(ActivityCreateDto createDto, Long userId);

  Activity updateActivity(ActivityUpdateDto activityUpdateDto);

  void deleteActivity(Long id);

  void deleteUserActivities(Long id, Long userId);

  List<ActivityDto> getActivities();

  List<ActivityDto> getUserActivities(Long userId);

}
