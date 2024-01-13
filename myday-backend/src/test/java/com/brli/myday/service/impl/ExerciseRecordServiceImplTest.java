package com.brli.myday.service.impl;

import com.brli.myday.dto.ExerciseRecordDto;
import com.brli.myday.service.ExerciseRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-25
 */
@SpringBootTest
class ExerciseRecordServiceImplTest {
  @Autowired
  ExerciseRecordService recordService;

  @Test
  void createRecord() {
    var recordDto = ExerciseRecordDto.builder().exerciseId(3L).duration(25F).build();
    var result = recordService.createRecord(recordDto, 2L);
    assertEquals(3L, result.getExerciseId());
    assertEquals(25F, result.getDuration());
  }

  @Test
  void updateRecord() {
    var recordDto = ExerciseRecordDto.builder().id(1L).exerciseId(9L).duration(15F).build();
    var result = recordService.updateRecord(recordDto);
    assertEquals(9L, result.getExerciseId());
    assertEquals(15F, result.getDuration());
  }

  @Test
  void deleteRecord() {
  }

  @Test
  void getRecords() {
    var recordList = recordService.getRecords();
    recordList.forEach(System.out::println);
  }

  @Test
  void getUserRecords() {
    var recordList = recordService.getUserRecords(1L);
    System.out.println(recordList.size());
  }

  @Test
  void createRecords() {
    var recordDto1 = ExerciseRecordDto.builder().exerciseId(1L).duration(12F).build();
    var recordDto2 = ExerciseRecordDto.builder().exerciseId(2L).duration(22F).build();
    var recordDto3 = ExerciseRecordDto.builder().exerciseId(3L).duration(32F).build();
    var result = recordService.createRecords(List.of(recordDto1,recordDto2,recordDto3), 2L);
    result.forEach(System.out::println);
  }
}