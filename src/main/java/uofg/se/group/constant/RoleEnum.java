package uofg.se.group.constant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public enum RoleEnum {

    PTT_DIRECTOR("PTT_DIRECTOR"),
    COURSE_DIRECTOR("COURSE_DIRECTOR"),
    STAFF("STAFF"),
    PLACEHOLDER("");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
