package uofg.se.group.constant;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public enum RoleEnum {

    PTT_DIRECTOR("PTTDirector"), COURSE_DIRECTOR("CourseDirector"), STAFF("Staff"), PLACEHOLDER("Placeholder");

    private final String type;

    RoleEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
