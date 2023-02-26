package uofg.se.group.constant;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public enum PersonTypeEnum {

    PTT_DIRECTOR("PTTDirector"),
    PLACEHOLDER("Placeholder");

    private final String type;

    PersonTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
