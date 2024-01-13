package com.brli.myday.service;

import com.brli.myday.dto.MeasurementDto;
import com.brli.myday.entity.Measurement;

import java.util.List;

/**
 * @author brandon
 * 2022-09-26
 */
public interface MeasurementService {

  Measurement createMeasurement(MeasurementDto measurementDto);

  Measurement updateMeasurement(MeasurementDto measurementDto);

  List<MeasurementDto> getUserMeasurement(Long userId);

}
