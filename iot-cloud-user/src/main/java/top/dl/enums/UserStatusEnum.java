package top.dl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * ⽤户状态
 *
 * @author minder
 **/
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
 /**
  * 停⽤
  */
 DISABLE(0, "停⽤"),
 /**
  * 正常
  */
 ENABLED(1, "正常");
 private final int value;
 private final String name;

 public static String getNameByValue(int value) {
  for (UserStatusEnum s : UserStatusEnum.values()) {
   if (s.getValue() == value) {
    return s.getName();
   }
  }
  return "";
 }

 public static Integer getValueByName(String name) {
  for (UserStatusEnum s : UserStatusEnum.values()) {
   if (Objects.equals(s.getName(), name)) {
    return s.getValue();
   }
  }
  return null;
 }
}