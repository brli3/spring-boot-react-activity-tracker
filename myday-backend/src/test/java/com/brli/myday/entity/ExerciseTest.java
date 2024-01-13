package com.brli.myday.entity;

import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.ExerciseCategoryRepository;
import com.brli.myday.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-13
 */
@SpringBootTest
class ExerciseTest {

  @Resource
  ExerciseRepository exerciseRepository;

  @Test
  void createExercise() {
    var exercise1 = Exercise.builder()
            .name("Exercise4")
            .description("desc4")
            .met(1.20f)
            .visible(true)
            .categoryId(2)
            .build();
    exerciseRepository.save(exercise1);
  }

  @Test
  void getCategory() {
    var exercise = exerciseRepository
            .findById(4L)
            .orElseThrow(() -> new ResourceNotFoundException("ID not found"));
  }

  @Test
  void getCategories() {
    var exercises = exerciseRepository.findByVisibleTrueAndCategoryId(1);
    exercises.forEach(e -> System.out.println(e.getName()));
    assertEquals(exercises.size(), 2);

    var exercises2 = exerciseRepository.findByVisibleTrueAndCategoryId(2);
    exercises2.forEach(e -> System.out.println(e.getName()));
    assertEquals(exercises2.size(), 1);
  }

}