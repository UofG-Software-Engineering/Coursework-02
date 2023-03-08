package uofg.se.group.constant;

/**
 * @Description Entity Enum
 * @Author Xiaohui Yu
 * @Date 2023/3/1
 */
public enum EntityEnum {
    COURSE("course.json"),
    PERSON("person.json"),
    Requirement("requirement.json"),
    SKILL("skill.json"),
    STAFF_SKILL("staffSkill.json"),
    TRAINING("training.json"),
    PLACEHOLDER("");

    private final String dataSourceFilePath;

    EntityEnum(String dataSourceFilePath) {
        this.dataSourceFilePath = dataSourceFilePath;
    }

    public String getDataSourceFilePath() {
        return Constant.DATA_DIR_PATH + "/" + dataSourceFilePath;
    }
}
