package com.course.server.util;

import com.course.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-03 22:26
 */

public class ValidatorUtil {

    /**
     * null（null or ""）
     */
    public static void require(Object str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidatorException(fieldName + " can not be Empty!");
        }
    }

    /**
     * length validation
     */
    public static void length(String str, String fieldName, int min, int max) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length < min || length > max) {
            throw new ValidatorException(fieldName + " length " + min + "~" + max);
        }
    }
}

