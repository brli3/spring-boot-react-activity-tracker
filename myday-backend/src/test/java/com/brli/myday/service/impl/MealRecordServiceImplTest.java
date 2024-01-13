package com.brli.myday.service.impl;

import com.brli.myday.dto.MealRecordDto;
import com.brli.myday.service.MealRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-25
 */
@SpringBootTest
class MealRecordServiceImplTest {
  @Autowired
  MealRecordService recordService;

  @Test
  void createRecord() {
    var mealRecordDto = MealRecordDto.builder().mealId(4L).servings(3F).build();
    var result = recordService.createRecord(mealRecordDto, 1L);
    assertEquals(4L, result.getMealId());
    assertEquals(1L, result.getUserId());
    assertEquals(3F, result.getServings());
  }

  @Test
  void updateRecord() {
    var mealRecordDto = MealRecordDto.builder().id(2L).mealId(3L).servings(2F).build();
    var result = recordService.updateRecord(mealRecordDto);
    assertEquals(3L, result.getMealId());
    assertEquals(2F, result.getServings());
  }

  @Test
  void deleteRecord() {
    recordService.deleteRecord(1L);
  }

  @Test
  void getRecords() {
    var recordList = recordService.getRecords();
    assertEquals(4, recordList.size());
  }

  @Test
  void getUserRecords() {
    var recordList = recordService.getUserRecords(1L);
    assertEquals(2, recordList.size());
  }
}