package com.brli.myday.repository;

import com.brli.myday.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

/**
 * @author brandon
 * 2022-09-25
 */
public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {

  List<MealRecord> findAllByUserId(Long userId);

}
