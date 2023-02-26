package uofg.se.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uofg.se.group.constant.PersonTypeEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CourseDirector extends BasePerson{

    private String courseId;

    public CourseDirector(String id, String name, PersonTypeEnum personType, String courseId) {
        super(id, name, personType);
        this.courseId = courseId;
    }
}
