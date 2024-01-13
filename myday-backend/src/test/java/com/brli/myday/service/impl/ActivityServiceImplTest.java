package com.brli.myday.service.impl;

import com.brli.myday.dto.*;
import com.brli.myday.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-25
 */
@SpringBootTest
class ActivityServiceImplTest {
  @Autowired
  ActivityService activityService;

  @Test
  void createActivity() {
    var mealRecordDtoList = List.of(
            MealRecordDto.builder().mealId(2L).servings(1F).build(),
            MealRecordDto.builder().mealId(1L).servings(1F).build(),
            MealRecordDto.builder().mealId(3L).servings(2F).build()
    );

    var exerciseRecordDtoList = List.of(
            ExerciseRecordDto.builder().exerciseId(1L).duration(20F).build(),
            ExerciseRecordDto.builder().exerciseId(2L).duration(4F).build(),
            ExerciseRecordDto.builder().exerciseId(3L).duration(40F).build(),
            ExerciseRecordDto.builder().exerciseId(4L).duration(10F).build(),
            ExerciseRecordDto.builder().exerciseId(5L).duration(50F).build()
    );

    var activityCreateDto = ActivityCreateDto
            .builder()
            .mealRecords(mealRecordDtoList)
            .exerciseRecords(exerciseRecordDtoList)
            .createdOn(LocalDate.of(2022,2,23))
            .build();

    var result = activityService.createActivity(activityCreateDto, 1L);
    assertEquals(3, result.getMealRecordIds().split("-").length);
    assertEquals(5, result.getExerciseRecordIds().split("-").length);
    assertEquals(LocalDate.of(2022, 2, 23), result.getCreatedOn());
    System.out.println(result);
  }

  @Test
  void createActivity2() {
    var mealRecordDtoList = List.of(
            MealRecordDto.builder().mealId(5L).servings(1.3F).build(),
            MealRecordDto.builder().mealId(2L).servings(1.2F).build(),
            MealRecordDto.builder().mealId(3L).servings(1F).build(),
            MealRecordDto.builder().mealId(1L).servings(0.3F).build()
    );

    var exerciseRecordDtoList = List.of(
            ExerciseRecordDto.builder().exerciseId(1L).duration(20F).build(),
            ExerciseRecordDto.builder().exerciseId(2L).duration(4F).build(),
            ExerciseRecordDto.builder().exerciseId(3L).duration(40F).build(),
            ExerciseRecordDto.builder().exerciseId(4L).duration(10F).build(),
            ExerciseRecordDto.builder().exerciseId(5L).duration(50F).build()
    );

    var activityCreateDto = ActivityCreateDto
            .builder()
            .mealRecords(mealRecordDtoList)
            .exerciseRecords(new ArrayList<>())
            .createdOn(LocalDate.of(2022,3,10))
            .build();

    var result = activityService.createActivity(activityCreateDto, 1L);
    assertEquals(4, result.getMealRecordIds().split("-").length);
    assertEquals(0, result.getExerciseRecordIds().length());

  }


  @Test
  void updateActivity() {
    var mealRecordDtoList = List.of(
            MealRecordDto.builder().mealId(4L).servings(1F).build(),
            MealRecordDto.builder().mealId(5L).servings(1F).build()
    );

    var exerciseRecordDtoList = List.of(
            ExerciseRecordDto.builder().exerciseId(3L).duration(20F).build(),
            ExerciseRecordDto.builder().exerciseId(2L).duration(40F).build(),
            ExerciseRecordDto.builder().exerciseId(1L).duration(10F).build()
    );

    var activityUpdateDto = ActivityUpdateDto.builder()
            .id(1L)
            .mealRecords(mealRecordDtoList)
            .exerciseRecords(exerciseRecordDtoList)
            .build();

    var result = activityService.updateActivity(activityUpdateDto);
    assertEquals(2, result.getMealRecordIds().split("-").length);
    assertEquals(3, result.getExerciseRecordIds().split("-").length);
  }

  @Test
  void deleteActivity() {
    activityService.deleteActivity(1L);
    assertEquals(1, activityService.getActivities().size());
  }

  @Test
  void deleteUserActivities() {
  }

  @Test
  void getActivities() {
    var activityDtoList = activityService.getActivities();
    assertEquals(2, activityDtoList.size());
  }

  @Test
  void getUserActivities() {
    var activityDtoList = activityService.getUserActivities(2L);
    activityDtoList.forEach(activityDto -> System.out.println(activityDto.getMealRecords()));
  }
}