package com.brli.myday.service;

import com.brli.myday.dto.ExerciseRecordDto;
import com.brli.myday.entity.ExerciseRecord;

import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
public interface ExerciseRecordService {

  ExerciseRecord createRecord(ExerciseRecordDto recordDto, Long userId);

  List<ExerciseRecord> createRecords(List<ExerciseRecordDto> recordDtos, Long userId);

  ExerciseRecord updateRecord(ExerciseRecordDto recordDto);

  void deleteRecord(Long id);

  List<ExerciseRecordDto> getRecords();

  List<ExerciseRecordDto> getUserRecords(Long userId);

}
