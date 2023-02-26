package uofg.se.group.exception;

import uofg.se.group.entity.Course;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}
