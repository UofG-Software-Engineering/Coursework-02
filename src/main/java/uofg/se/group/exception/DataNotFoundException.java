package uofg.se.group.exception;

/**
 * @Description Data not found exception
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String id) {
        super("Data not found with id: " + id);
    }
}
