package com.brli.myday.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brandon
 * 2022-09-24
 */
class StringUtilsTest {

  @Test
  void formatDateTime() {
  }

  @Test
  void concatToStrList() {
    var concatStr = "12-23-34";
    var strList = StringUtils.concatToStrList(concatStr);
    assertEquals("12", strList.get(0));
    assertEquals("23", strList.get(1));
    assertEquals("34", strList.get(2));
  }

  @Test
  void concatToLongList() {
    var concatStr = "12-23-34";
    var strList = StringUtils.concatToLongList(concatStr);
    assertEquals(12L, strList.get(0));
    assertEquals(23L, strList.get(1));
    assertEquals(34L, strList.get(2));
  }

  @Test
  void listToConcat() {
    var strList = List.of("12", "23", "34");
    var concatStr = StringUtils.listToConcat(strList);
    assertEquals("12-23-34", concatStr);
  }

  @Test
  void listToConcat2() {
    var list = List.of(12L, 23L, 34L);
    var concatStr = StringUtils.listToConcat(list);
    assertEquals("12-23-34", concatStr);
  }
}