package org.purplebean.kmip.util;

public class StringUtils {

  public static String convertPascalToTitleCase(String name) {
    return name.replaceAll("([a-z])([A-Z])", "$1 $2");
  }

  public static String convertTitleToPascalCase(String name) {
    return name.replaceAll("([a-z]) ([A-Z])", "$1$2");
  }
}
