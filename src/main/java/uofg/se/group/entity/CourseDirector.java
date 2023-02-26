package uofg.se.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class CourseDirector extends BasePerson{

    private String courseId;
}
