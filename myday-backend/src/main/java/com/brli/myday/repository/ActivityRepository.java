package com.brli.myday.repository;

import com.brli.myday.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author brandon
 * 2022-09-23
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

  boolean existsByCreatedOnAndUserId(LocalDate createdOn, Long userId);

  Activity findByCreatedOnAndUserId(LocalDate createdOn, Long userId);

  List<Activity> findByUserId(Long id);

  List<Activity> findByUserIdOrderByCreatedOn(Long userId);

}
