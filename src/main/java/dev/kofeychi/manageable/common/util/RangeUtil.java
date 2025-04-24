package dev.kofeychi.manageable.common.util;

import dev.kofeychi.manageable.common.window.AbstractWindow;

public class RangeUtil {
    public static boolean isInRange(int val,int bound){
        return bound >= val && val >= -bound;
    }
}
