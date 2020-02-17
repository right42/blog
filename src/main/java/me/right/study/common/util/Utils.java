package me.right.study.common.util;

import java.util.Collection;

public class Utils {

    public static boolean isNotNullAndNotEmpty(Collection<?> collection) {
        return (collection != null) && (!collection.isEmpty());
    }

}
