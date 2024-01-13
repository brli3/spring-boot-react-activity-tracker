package com.brli.myday.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brandon
 * 2022-09-12
 */
public class StringUtils {
  /**
   * Format original LocalDataTime format to xxxx-xx-xx xx:xx:xx.xxx
   * @param time LocalDateTime object
   * @return Year-month-day hour:minute:second.nano
   */
  public static String formatDateTime(LocalDateTime time) {
    return time.getYear() + "-" + time.getMonthValue() + "-" + time.getDayOfMonth() + " " +
           time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "." +
           String.valueOf(time.getNano()).substring(0, 3);
  }

  /**
   * Convert concatenated a string to list
   * @param concatStr concatenated string e.g. 1128-2231-3281
   * @return a list of separated substrings
   */
  public static List<String> concatToStrList(String concatStr) {
    if ("".equals(concatStr)) return new ArrayList<>();
    return Arrays.stream(concatStr.split("-")).toList();
  }

  public static List<Long> concatToLongList(String concatStr) {
    if ("".equals(concatStr)) return new ArrayList<>();
    return Arrays.stream(concatStr.split("-")).map(Long::parseLong).toList();
  }

  /**
   * Convert a generic list to a concatenated string
   * @param list e.g. [1,2,3]
   * @param <T> Integer, Long, float, String
   * @return String e.g. 1-2-3
   */
  public static <T> String listToConcat(List<T> list) {
    var strList = list.stream().map(String::valueOf).toList();
    return String.join("-", strList);
  }

}
