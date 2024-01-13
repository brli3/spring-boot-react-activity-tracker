package com.brli.myday.dto;

import com.brli.myday.entity.ExerciseCategory;
import com.brli.myday.repository.ExerciseCategoryRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-15
 */
@SpringBootTest
class ExerciseCategoryDtoTest {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ExerciseCategoryRepository categoryRepository;

  @Test
  void categoryMapper() {
    var categoryDTO = ExerciseCategoryDto.builder()
            .name("category1").description("desc1").build();
    ExerciseCategory category = modelMapper.map(categoryDTO, ExerciseCategory.class);
    assertEquals(category.getName(), categoryDTO.getName());
    System.out.println("Category: " + category.getId() + category.getName());
    System.out.println("Category DTO: " + categoryDTO);
  }

  @Test
  void mapToDTO() {
    var entity = categoryRepository.findByVisibleTrueAndId(1).get();
    var dto = modelMapper.map(entity, ExerciseCategoryDto.class);
    System.out.println(dto);
  }
}