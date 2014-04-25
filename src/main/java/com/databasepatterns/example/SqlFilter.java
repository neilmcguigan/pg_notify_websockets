package com.databasepatterns.example;

import java.util.regex.Pattern;

/**
 * @author: nmcguigan@rbauction.com
 * @since: 4/24/14
 */
public class SqlFilter {

    public static String identifier(String value) {
        if(!value.matches("(?i)^[a-z_][a-z_0-9\\$]{0,63}$")) {
            throw new RuntimeException("Channel name must be a valid SQL identifier.");
        }
        return value;
    }
}
