package org.purpleBean.kmip;

public class StringUtils {

    public static String covertPascalToTitleCase(String name) {
        return name.replaceAll("([a-z])([A-Z])", "$1 $2");
    }

    public static String covertTitleToPascalCase(String name) {
        return name.replaceAll("([a-z]) ([A-Z])", "$1$2");
    }
}
