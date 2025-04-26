package org.example.Functional.ToolsString;

public class ToolsString {
    public static String limitStringLength(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }
}
