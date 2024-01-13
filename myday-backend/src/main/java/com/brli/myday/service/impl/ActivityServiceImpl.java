package com.brli.myday.service.impl;

import com.brli.myday.dto.*;
import com.brli.myday.entity.*;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.*;
import com.brli.myday.service.*;
import com.brli.myday.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ExerciseRecordService exerciseRecordService;

  @Autowired
  MealRecordService mealRecordService;

  @Autowired
  MealRepository mealRepository;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Autowired
  ActivityRepository activityRepository;

  @Autowired
  MeasurementRepository measurementRepository;

  @Autowired
  MealRecordRepository mealRecordRepository;

  @Autowired
  ExerciseRecordRepository exerciseRecordRepository;


  private float getCaloriesFromMet(float weightInKg, float met, float durationInMin) {
    return (weightInKg * met * durationInMin * 3.5F) / 200F;
  }

  private float getCaloriesFromMealRecords(List<MealRecord> mealRecords) {
    var calories = 0.F;
    for (var record : mealRecords) {
      var caloriesPerServing = mealRepository.findById(record.getMealId())
              .map(Meal::getCalPerServing).orElse(0.F);
      calories += caloriesPerServing * record.getServings();
    }
    return calories;
  }

  private float getCaloriesFromExerciseRecords(List<ExerciseRecord> exerciseRecords, float weight) {
    var calories = 0.F;
    for (var record : exerciseRecords) {
      var met = exerciseRepository.findById(record.getExerciseId())
              .map(Exercise::getMet).orElse(0.F);
      calories += getCaloriesFromMet(weight, met, record.getDuration());
    }
    return calories;
  }

  /**
   * Save exercise and meal records; save activity
   * @param activityCreateDto createDto including meal and exercise DTOs
   * @param userId creator ID
   * @return Saved activity
   */
  @Override
  public Activity createActivity(ActivityCreateDto activityCreateDto, Long userId) {
    var activity = modelMapper.map(activityCreateDto, Activity.class);
    // Set to current user
    activity.setUserId(userId);

    // update activity instead if it's already created, i.e. with same user and date
    if (activityRepository.existsByCreatedOnAndUserId(activity.getCreatedOn(), userId)) {
      log.info("Activity on this date already exists. Will update it instead");
      var activityUpdateDto = modelMapper.map(activity, ActivityUpdateDto.class);
      var id = activityRepository.findByCreatedOnAndUserId(activity.getCreatedOn(), userId).getId();
      activityUpdateDto.setId(id);
      return updateActivity(activityUpdateDto);
    }

    var mealRecordDtos = activityCreateDto.getMealRecords();
    mealRecordDtos.forEach(recordDto -> recordDto.setCreatedOn(activity.getCreatedOn()));
    // Save meal records and return them for further use
    var mealRecords = mealRecordService.createRecords(mealRecordDtos, userId);

    var mealRecordIdList = mealRecords.stream()
            .map(MealRecord::getId).toList();
    activity.setMealRecordIds(StringUtils.listToConcat(mealRecordIdList));
    activity.setCaloriesIn(getCaloriesFromMealRecords(mealRecords));

    var exerciseRecordDtos = activityCreateDto.getExerciseRecords();
    exerciseRecordDtos.forEach(recordDto -> recordDto.setCreatedOn(activity.getCreatedOn()));
    // Save exercise records and return them for further use
    var exerciseRecords = exerciseRecordService.createRecords(exerciseRecordDtos, userId);

    // Get most recent weight measurement for computing burned calories
    var measurementList = measurementRepository.findAllByUserIdOrderByCreatedOn(userId);
    var weight = measurementList.get(measurementList.size() - 1).getWeight();
    var exerciseRecordIdList = exerciseRecords.stream().map(ExerciseRecord::getId).toList();
    activity.setExerciseRecordIds(StringUtils.listToConcat(exerciseRecordIdList));
    activity.setCaloriesOut(getCaloriesFromExerciseRecords(exerciseRecords, weight));
    return activityRepository.save(activity);
  }


  /**
   * Delete meal records by IDs (saved in activity)
   * @param idsStr concatenated record IDs e.g. "1-23-233"
   */
  private void deleteMealRecordsByIdsStr(String idsStr) {
    var idList = StringUtils.concatToLongList(idsStr);
    if (!idList.isEmpty()) {
      idList.forEach(mealRecordService::deleteRecord);
    }
  }

  /**
   * Delete exercise records by IDs (saved in activity)
   * @param idsStr concatenated record IDs e.g. "1-23-233"
   */
  private void deleteExerciseRecordsByIdsStr(String idsStr) {
    var idList = StringUtils.concatToLongList(idsStr);
    if (!idList.isEmpty()) {
      idList.forEach(exerciseRecordService::deleteRecord);
    }
  }

  @Override
  public Activity updateActivity(ActivityUpdateDto activityUpdateDto) {
    var oldActivity= activityRepository
            .findById(activityUpdateDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));

    var activity = modelMapper.map(oldActivity, Activity.class);

    var userId = activity.getUserId();

    // delete old meal records
    deleteMealRecordsByIdsStr(oldActivity.getMealRecordIds());
    // save new records
    var mealRecordDtos = activityUpdateDto.getMealRecords();
    mealRecordDtos.forEach(recordDto -> recordDto.setCreatedOn(activity.getCreatedOn()));
    var mealRecords = mealRecordService.createRecords(mealRecordDtos, userId);
    var mealRecordIdList = mealRecords.stream()
            .map(MealRecord::getId).toList();
    activity.setMealRecordIds(StringUtils.listToConcat(mealRecordIdList));
    activity.setCaloriesIn(getCaloriesFromMealRecords(mealRecords));

    // delete old exercise records
    deleteExerciseRecordsByIdsStr(oldActivity.getExerciseRecordIds());
    // save new records
    var exerciseRecordDtos = activityUpdateDto.getExerciseRecords();
    exerciseRecordDtos.forEach(recordDto -> recordDto.setCreatedOn(activity.getCreatedOn()));
    var exerciseRecords = exerciseRecordService.createRecords(exerciseRecordDtos, userId);

    // Get most recent weight measurement for computing burned calories
    var measurementList = measurementRepository.findAllByUserIdOrderByCreatedOn(userId);
    var weight = measurementList.get(measurementList.size() - 1).getWeight();
    var exerciseRecordIdList = exerciseRecords.stream().map(ExerciseRecord::getId).toList();
    activity.setExerciseRecordIds(StringUtils.listToConcat(exerciseRecordIdList));
    activity.setCaloriesOut(getCaloriesFromExerciseRecords(exerciseRecords, weight));

    return activityRepository.save(activity);
  }

  @Override
  public void deleteActivity(Long id) {
    var activity= activityRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
    // delete related meal and exercise records
    deleteMealRecordsByIdsStr(activity.getMealRecordIds());
    deleteExerciseRecordsByIdsStr(activity.getExerciseRecordIds());
    activityRepository.deleteById(id);
  }

  @Override
  public void deleteUserActivities(Long id, Long userId) {
    var activities = activityRepository.findByUserId(userId);
    if (activities.isEmpty()) return;
    // delete each activity and related meal and exercise records
    activities.stream().map(Activity::getId).forEach(this::deleteActivity);
  }

  private List<MealRecordDto> getMealRecordDtosFromActivity(Activity activity) {
    var recordIdsStr = activity.getMealRecordIds();
    var recordIdsList = StringUtils.concatToLongList(recordIdsStr);
    return recordIdsList.stream().map(
            id -> mealRecordRepository.findById(id)
                    .map(mealRecord -> modelMapper.map(mealRecord, MealRecordDto.class))
                    .orElseThrow(() -> new ResourceNotFoundException("Meal record not found")))
            .toList();
  }

  private List<ExerciseRecordDto> getExerciseRecordDtosFromActivity(Activity activity) {
    var recordIdsStr = activity.getMealRecordIds();
    var recordIdsList = StringUtils.concatToLongList(recordIdsStr);
    return recordIdsList.stream().map(
                    id -> exerciseRecordRepository.findById(id)
                            .map(mealRecord -> modelMapper.map(mealRecord, ExerciseRecordDto.class))
                            .orElseThrow(() -> new ResourceNotFoundException("Meal record not found")))
            .toList();
  }

  private ActivityDto getActivityDto(Activity activity) {
    var activityDto = modelMapper.map(activity, ActivityDto.class);
    activityDto.setMealRecords(getMealRecordDtosFromActivity(activity));
    activityDto.setExerciseRecords(getExerciseRecordDtosFromActivity(activity));
    return activityDto;
  }

  @Override
  public List<ActivityDto> getActivities() {
    var activities = activityRepository.findAll();
    return activities.stream().map(this::getActivityDto).toList();
  }

  /**
   * Get activity list from current user; order by date
   * @param userId user ID
   * @return list of activity
   */
  @Override
  public List<ActivityDto> getUserActivities(Long userId) {
    var activities = activityRepository.findByUserIdOrderByCreatedOn(userId);
    return activities.stream().map(this::getActivityDto).toList();
  }
}
