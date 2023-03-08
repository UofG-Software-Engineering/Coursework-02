package uofg.se.group.exception;

import uofg.se.group.constant.RoleEnum;

/**
 * @Description Permission error exception
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
public class PermissionErrorException extends RuntimeException {

    public PermissionErrorException(RoleEnum role, String personId) {
        super("Permission error for Person with id: " + personId + " and role: " + role.getValue());
    }
}
