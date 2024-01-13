package com.brli.myday.service;

import com.brli.myday.dto.ExerciseCategoryDto;
import com.brli.myday.dto.ExerciseDto;

import java.util.List;

/**
 * @author brandon
 * 2022-09-16
 */
public interface ExerciseService {

  void createCategory(ExerciseCategoryDto categoryDTO);

  void updateCategory(ExerciseCategoryDto categoryDTO);

  void deleteCategory(Integer id);

  List<ExerciseCategoryDto> getVisibleCategories();

  List<ExerciseCategoryDto> getAllCategories();

  void createExercise(ExerciseDto exerciseDto);

  void updateExercise(ExerciseDto exerciseDto);

  void deleteExercise(Long id);

  List<ExerciseDto> getVisibleExercises();

  List<ExerciseDto> getVisibleExercises(String name);

  List<ExerciseDto> getAllExercises();

  List<ExerciseDto> getVisibleExercisesByCategory(Integer id);

  List<ExerciseDto> getVisibleExercisesByCategoryName(String name);

}
