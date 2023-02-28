package uofg.se.group.pojo.vo;

import java.util.List;
import lombok.Builder;
import lombok.ToString;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Skill;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/28
 */
@Builder
@ToString
public class RequirementVo {

    private String id;
    private Course course;
    private Person CourseDirector;
    private Person staff;
    private List<Skill> skills;
    private RequirementStatusEnum status;
}
