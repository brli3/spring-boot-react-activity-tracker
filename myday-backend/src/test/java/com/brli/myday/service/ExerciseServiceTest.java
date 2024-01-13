package com.brli.myday.service;

import com.brli.myday.dto.ExerciseDto;
import com.brli.myday.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author brandon
 * 2022-09-16
 */
@SpringBootTest
class ExerciseServiceTest {

  @Autowired
  ExerciseService exerciseService;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Test
  void createExercise() {
    var exerciseDTO = ExerciseDto.builder()
            .name("exercise4").description("desc4").categoryId(1).met(5F)
            .build();
    exerciseService.createExercise(exerciseDTO);
  }

  @Test
  void updateExercise() {
    var exerciseDTO = ExerciseDto.builder()
            .id(3L).name("exercise3b").description("desc3").categoryId(2).met(7F)
            .build();
    exerciseService.updateExercise(exerciseDTO);
  }

  @Test
  void deleteExercise() {
    exerciseService.deleteExercise(3L);
  }

  @Test
  void getVisibleExercises() {

  }

  @Test
  void getAllExercises() {
  }

  @Test
  void getVisibleExercisesByCategory() {
  }
}