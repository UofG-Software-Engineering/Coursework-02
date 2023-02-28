package uofg.se.group.repo;

import org.springframework.stereotype.Component;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.pojo.entity.Course;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/28
 */
@Component
public class CourseRepo extends BaseRepo<Course> {

    public boolean existsByCourseIdAndCourseDirectorId(String courseId, String courseDirectorId) {
        boolean isExisted = findAll().stream().anyMatch(
                course -> course.getId().equals(courseId) && course.getCourseDirectorId().equals(courseDirectorId));
        if (!isExisted) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        return true;
    }
}
