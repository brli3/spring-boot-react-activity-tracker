package com.brli.myday.service.impl;

import com.brli.myday.dto.ExerciseCategoryDto;
import com.brli.myday.dto.ExerciseDto;
import com.brli.myday.entity.Exercise;
import com.brli.myday.entity.ExerciseCategory;
import com.brli.myday.exception.ResourceInUseException;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.ExerciseCategoryRepository;
import com.brli.myday.repository.ExerciseRepository;
import com.brli.myday.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brandon
 * 2022-09-16
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Autowired
  ExerciseCategoryRepository categoryRepository;

  private ExerciseCategory getCategoryFromDTO(ExerciseCategoryDto categoryDTO) {
    var avatar = categoryDTO.getAvatar();
    if (avatar == null || "".equals(avatar.trim())) {
      categoryDTO.setAvatar("https://cdn2.vectorstock.com/i/thumb-large/34/01/running-man-cartoon-character-jogging-sport-vector-37433401.jpg");
    }
    return modelMapper.map(categoryDTO, ExerciseCategory.class);
  }

  private ExerciseCategoryDto getCategoryDTO(ExerciseCategory category) {
    return modelMapper.map(category, ExerciseCategoryDto.class);
  }

  private Exercise getExerciseFromDTO(ExerciseDto exerciseDTO) {
    var categoryId = exerciseDTO.getCategoryId();
    categoryRepository.findByVisibleTrueAndId(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category ID not found"));
    var avatar = exerciseDTO.getAvatar();
    if (avatar == null || "".equals(avatar.trim())) {
      exerciseDTO.setAvatar("https://static.thenounproject.com/png/2397491-200.png");
    }
    return modelMapper.map(exerciseDTO, Exercise.class);
  }

  private ExerciseDto getExerciseDTO(Exercise exercise) {
    var exerciseDto = modelMapper.map(exercise, ExerciseDto.class);
    var categoryId = exercise.getCategoryId();
    if (categoryId != null && exercise.isVisible()) {
      var categoryName = categoryRepository
              .findByVisibleTrueAndId(categoryId)
              .map(ExerciseCategory::getName)
              .orElse(null);
      exerciseDto.setCategory(categoryName);
    }
    return exerciseDto;
  }

  @Override
  public void createCategory(ExerciseCategoryDto categoryDTO) {
    var category = modelMapper.map(categoryDTO, ExerciseCategory.class);
    categoryRepository.save(category);
  }

  @Override
  public void updateCategory(ExerciseCategoryDto categoryDTO) {
    categoryRepository.findByVisibleTrueAndId(categoryDTO.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    categoryRepository.save(getCategoryFromDTO(categoryDTO));
  }

  @Override
  public void deleteCategory(Integer id) {
    var category = categoryRepository.findByVisibleTrueAndId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    var exercises = exerciseRepository.findByVisibleTrueAndCategoryId(id);
    if (!exercises.isEmpty()) {
      throw new ResourceInUseException("Category is still used by other exercises");
    }
    category.setVisible(false);
    categoryRepository.save(category);
    exerciseRepository.findByVisibleTrueAndCategoryId(id).forEach(e -> {
      e.setCategoryId(null);
      exerciseRepository.save(e);
    });
  }

  @Override
  public List<ExerciseCategoryDto> getVisibleCategories() {
    var categoryList = categoryRepository.findByVisibleTrue();
    return categoryList.stream()
            .map(this::getCategoryDTO)
            .collect(Collectors.toList());
  }

  @Override
  public List<ExerciseCategoryDto> getAllCategories() {
    var categoryList = categoryRepository.findAll();
    return categoryList.stream()
            .map(this::getCategoryDTO)
            .collect(Collectors.toList());
  }

  @Override
  public void createExercise(ExerciseDto exerciseDto) {
    var exercise = getExerciseFromDTO(exerciseDto);
    exerciseRepository.save(exercise);
  }

  @Override
  public void updateExercise(ExerciseDto exerciseDto) {
    exerciseRepository.findByVisibleTrueAndId(exerciseDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Exercise ID not found"));
    var exercise = getExerciseFromDTO(exerciseDto);
    exerciseRepository.save(exercise);
  }

  @Override
  public void deleteExercise(Long id) {
    var exercise = exerciseRepository.findByVisibleTrueAndId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Exercise ID not found"));
    exercise.setVisible(false);
    exerciseRepository.save(exercise);
  }

  @Override
  public List<ExerciseDto> getVisibleExercises() {
    return exerciseRepository.findByVisibleTrue().stream()
            .map(this::getExerciseDTO).collect(Collectors.toList());
  }

  @Override
  public List<ExerciseDto> getVisibleExercises(String name) {
    return exerciseRepository.findByVisibleTrueAndNameContains(name).stream()
            .map(this::getExerciseDTO).collect(Collectors.toList());
  }

  @Override
  public List<ExerciseDto> getAllExercises() {
    return exerciseRepository.findAll().stream()
            .map(this::getExerciseDTO).collect(Collectors.toList());
  }

  @Override
  public List<ExerciseDto> getVisibleExercisesByCategory(Integer id) {
    return exerciseRepository.findByVisibleTrueAndCategoryId(id).stream()
            .map(this::getExerciseDTO).collect(Collectors.toList());
  }

  @Override
  public List<ExerciseDto> getVisibleExercisesByCategoryName(String name) {
    var categories= categoryRepository.findByVisibleTrueAndNameContains(name);
    return categories.stream()
            .map(category -> exerciseRepository.findByVisibleTrueAndCategoryId(category.getId()))
            .flatMap(List::stream).map(this::getExerciseDTO).toList();
  }
}
