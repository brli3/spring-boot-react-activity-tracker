package com.brli.myday.entity;

import com.brli.myday.repository.ExerciseCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-13
 */
@SpringBootTest
class ExerciseCategoryTest {

  @Resource
  ExerciseCategoryRepository categoryRepository;

  @Test
  void createCategory() {
    var category = ExerciseCategory.builder()
            .name("category3")
            .description("category3 desc")
            .visible(true)
            .build();
    categoryRepository.save(category);
  }

}