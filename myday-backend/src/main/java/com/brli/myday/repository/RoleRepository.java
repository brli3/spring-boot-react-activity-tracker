package com.brli.myday.repository;

import com.brli.myday.entity.Role;
import com.brli.myday.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author brandon
 * 2022-06-28 00:09
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

  boolean existsByName(String name);

}
