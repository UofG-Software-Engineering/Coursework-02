package uofg.se.group.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/28
 */
@Component
public class CourseRepo extends BaseRepo<Course> {

    public CourseRepo() {
        super("data/course.json");
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
