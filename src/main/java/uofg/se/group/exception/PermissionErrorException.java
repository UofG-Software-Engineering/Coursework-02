package uofg.se.group.exception;

import uofg.se.group.constant.PersonTypeEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class PermissionErrorException extends RuntimeException {

    public PermissionErrorException(PersonTypeEnum personType, String message) {
        super(message);
    }
}
