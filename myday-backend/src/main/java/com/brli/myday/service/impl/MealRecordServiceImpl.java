package com.brli.myday.service.impl;

import com.brli.myday.dto.MealRecordDto;
import com.brli.myday.entity.MealRecord;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.MealRecordRepository;
import com.brli.myday.repository.MealRepository;
import com.brli.myday.service.MealRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
@Service
public class MealRecordServiceImpl implements MealRecordService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  MealRecordRepository recordRepository;

  @Autowired
  MealRepository mealRepository;

  @Override
  public MealRecord createRecord(MealRecordDto recordDto, Long userId) {
    mealRepository.findById(recordDto.getMealId())
            .orElseThrow(() -> new ResourceNotFoundException("Meal not in database"));
    var record = modelMapper.map(recordDto, MealRecord.class);
    record.setUserId(userId);
    return recordRepository.save(record);
  }

  @Override
  public List<MealRecord> createRecords(List<MealRecordDto> recordDtos, Long userId) {
    return recordDtos.stream()
            .map(recordDto -> createRecord(recordDto, userId))
            .toList();
  }

  @Override
  public MealRecord updateRecord(MealRecordDto recordDto) {
    mealRepository.findById(recordDto.getMealId())
            .orElseThrow(() -> new ResourceNotFoundException("Meal not in database"));
    var record = modelMapper.map(recordDto, MealRecord.class);
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
  public List<MealRecordDto> getRecords() {
    return recordRepository.findAll().stream()
            .map(record -> modelMapper.map(record, MealRecordDto.class))
            .toList();
  }

  @Override
  public List<MealRecordDto> getUserRecords(Long userId) {
    return recordRepository.findAllByUserId(userId).stream()
            .map(record -> modelMapper.map(record, MealRecordDto.class))
            .toList();
  }
}
