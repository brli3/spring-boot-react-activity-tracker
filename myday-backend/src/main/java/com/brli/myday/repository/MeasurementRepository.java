package com.brli.myday.repository;

import com.brli.myday.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author brandon
 * 2022-09-25
 */
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

  List<Measurement> findAllByUserIdOrderByCreatedOn(Long userId);

  Optional<Measurement> findByUserIdAndCreatedOn(Long userId, LocalDate createdOn);

}
