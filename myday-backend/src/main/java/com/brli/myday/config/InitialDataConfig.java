package com.brli.myday.config;

import com.brli.myday.entity.Role;
import com.brli.myday.enums.RoleEnum;
import com.brli.myday.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/** Insert initial records into database
 * @author brandon
 * 2022-09-24
 */
@Configuration
public class InitialDataConfig {

  /**
   * Insert roles into database based on RoleEnum
   * @param roleRepository role repository
   * @return none
   */
  @Bean
  public ApplicationRunner addRoles(RoleRepository roleRepository) {
    return args -> {
      Arrays.stream(RoleEnum.values()).forEach(roleEnum ->
              roleRepository.save(new Role(roleEnum.getId(), roleEnum.getName())));
    };
  }

}
