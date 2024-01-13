package com.brli.myday.service;

import com.brli.myday.dto.MealRecordDto;
import com.brli.myday.entity.MealRecord;

import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
public interface MealRecordService {

  MealRecord createRecord(MealRecordDto recordDto, Long userId);

  List<MealRecord> createRecords(List<MealRecordDto> recordDtos, Long userId);

  MealRecord updateRecord(MealRecordDto recordDto);

  void deleteRecord(Long id);

  List<MealRecordDto> getRecords();

  List<MealRecordDto> getUserRecords(Long userId);

}
