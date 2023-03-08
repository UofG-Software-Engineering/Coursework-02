package uofg.se.group.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.pojo.entity.Course;

/**
 * @Description Course repository
 * @Author Xiaohui Yu
 * @Date 2023/2/28
 */
@Component
public class CourseRepo extends BaseRepo<Course> {

    public CourseRepo() {
        super(EntityEnum.COURSE.getDataSourceFilePath());
    }

    public boolean existsByCourseIdAndCourseDirectorId(String courseId, String courseDirectorId) {
        return findAll().stream().anyMatch(
                course -> course.getId().equals(courseId) && course.getCourseDirectorId().equals(courseDirectorId));
    }

    @Override
    public List<Course> findAll() {
        return (List<Course>) jsonReader.read(dataSourceFilePath, Course.class);
    }
}
