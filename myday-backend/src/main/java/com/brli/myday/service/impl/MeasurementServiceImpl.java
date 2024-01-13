package com.brli.myday.service.impl;

import com.brli.myday.dto.MeasurementDto;
import com.brli.myday.entity.Measurement;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.MeasurementRepository;
import com.brli.myday.service.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author brandon
 * 2022-09-27
 */
@Service
public class MeasurementServiceImpl implements MeasurementService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  MeasurementRepository measurementRepository;

  @Override
  public Measurement createMeasurement(MeasurementDto measurementDto) {
    var createdOn = measurementDto.getCreatedOn();
    var userId = measurementDto.getUserId();
    var _measurement = measurementRepository.findByUserIdAndCreatedOn(userId, createdOn);
    // delete if user has already created a measurement on the same date
    _measurement.ifPresent(measurement -> measurementRepository.deleteById(measurement.getId()));
    var measurement = modelMapper.map(measurementDto, Measurement.class);
    return measurementRepository.save(measurement);
  }

  @Override
  public Measurement updateMeasurement(MeasurementDto measurementDto) {
    measurementRepository.findById(measurementDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Measurement not found"));
    var measurement = modelMapper.map(measurementDto, Measurement.class);
    return measurementRepository.save(measurement);
  }

  @Override
  public List<MeasurementDto> getUserMeasurement(Long userId) {
    return measurementRepository
            .findAllByUserIdOrderByCreatedOn(userId)
            .stream()
            .map(measurement -> modelMapper.map(measurement, MeasurementDto.class))
            .toList();
  }
}
