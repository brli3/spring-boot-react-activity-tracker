package com.brli.myday.repository;

import com.brli.myday.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {

  List<ExerciseRecord> findAllByUserId(Long userId);

}
