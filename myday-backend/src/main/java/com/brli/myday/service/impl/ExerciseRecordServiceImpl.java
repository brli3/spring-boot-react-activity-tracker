package com.brli.myday.service.impl;

import com.brli.myday.dto.ExerciseRecordDto;
import com.brli.myday.entity.ExerciseRecord;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.ExerciseRecordRepository;
import com.brli.myday.repository.ExerciseRepository;
import com.brli.myday.service.ExerciseRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ExerciseRecordRepository recordRepository;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Override
  public ExerciseRecord createRecord(ExerciseRecordDto recordDto, Long userId) {
    exerciseRepository.findById(recordDto.getExerciseId())
            .orElseThrow(() -> new ResourceNotFoundException("Exercise not in database"));
    var record = modelMapper.map(recordDto, ExerciseRecord.class);
    record.setUserId(userId);
    return recordRepository.save(record);
  }

  @Override
  public List<ExerciseRecord> createRecords(List<ExerciseRecordDto> recordDtos, Long userId) {
    return recordDtos.stream()
            .map(recordDto -> createRecord(recordDto, userId))
            .toList();
  }

  @Override
  public ExerciseRecord updateRecord(ExerciseRecordDto recordDto) {
    exerciseRepository.findById(recordDto.getExerciseId())
            .orElseThrow(() -> new ResourceNotFoundException("Exercise not in database"));
    var record = modelMapper.map(recordDto, ExerciseRecord.class);
    var _record = recordRepository.findById(record.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    record.setUserId(_record.getUserId());
    recordRepository.save(record);
    return record;
  }

  @Override
  public void deleteRecord(Long id) {
    recordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    recordRepository.deleteById(id);
  }

  @Override
  public List<ExerciseRecordDto> getRecords() {
    return recordRepository.findAll().stream()
            .map(record -> modelMapper.map(record, ExerciseRecordDto.class))
            .toList();
  }

  @Override
  public List<ExerciseRecordDto> getUserRecords(Long userId) {
    return recordRepository.findAllByUserId(userId).stream()
            .map(record -> modelMapper.map(record, ExerciseRecordDto.class))
            .toList();
  }
}
