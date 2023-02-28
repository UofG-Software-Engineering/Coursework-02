package uofg.se.group.exception;

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
