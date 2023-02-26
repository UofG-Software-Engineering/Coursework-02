package uofg.se.group.inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class ReflectUtil {

    private ReflectUtil() {
    }

    public static boolean trySetAccessible(Constructor constructor) {
        if (constructor.isAccessible()) {
            return true;
        }
        constructor.setAccessible(true);
        return true;
    }

    public static boolean trySetAccessible(Method method) {
        if (method.isAccessible()) {
            return true;
        }
        method.setAccessible(true);
        return true;
    }

    public static boolean trySetAccessible(Field field) {
        if (field.isAccessible()) {
            return true;
        }
        field.setAccessible(true);
        return true;
    }
}