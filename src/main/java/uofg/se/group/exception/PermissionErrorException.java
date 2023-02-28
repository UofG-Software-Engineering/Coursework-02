package uofg.se.group.exception;

import uofg.se.group.constant.RoleEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class PermissionErrorException extends RuntimeException {

    public PermissionErrorException(RoleEnum role, String message) {
        super(message);
    }
}
