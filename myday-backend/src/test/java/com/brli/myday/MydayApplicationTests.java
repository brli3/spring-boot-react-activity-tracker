package com.brli.myday;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MydayApplicationTests {

  @Test
  void contextLoads(ApplicationContext context) {
    System.out.println(context.containsBean("modelMapper"));
  }

}
