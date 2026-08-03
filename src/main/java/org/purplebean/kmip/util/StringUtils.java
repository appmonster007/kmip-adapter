package org.purplebean.kmip.util;

/**
 * Utility methods for converting between naming conventions used by KMIP identifiers.
 */
public class StringUtils {

  /**
   * Converts a PascalCase or camelCase name to Title Case (e.g. {@code fooBar} becomes {@code
   * foo Bar}).
   */
  public static String convertPascalToTitleCase(String name) {
    return name.replaceAll("([a-z])([A-Z])", "$1 $2");
  }

  /**
   * Converts a Title Case name back to camelCase (e.g. {@code foo Bar} becomes {@code fooBar}).
   */
  public static String convertTitleToPascalCase(String name) {
    return name.replaceAll("([a-z]) ([A-Z])", "$1$2");
  }
}
