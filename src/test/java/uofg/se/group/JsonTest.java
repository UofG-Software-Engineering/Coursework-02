package uofg.se.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uofg.se.group.Application;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.util.JsonReader;
import uofg.se.group.util.JsonWriter;

/**
 * @Description Json writer and reader test
 * @Author Xiaohui Yu
 * @Date 2023/3/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JsonTest {

    @Resource
    private JsonWriter jsonWriter;
    @Resource
    private JsonReader jsonReader;

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        String filePath = "data/Course.json";
        Course course = new Course();
        course.setName("Big Data");
        List<Course> courses = new ArrayList<>() {{
            add(course);
            add(course);
        }};
        jsonWriter.write(filePath, courses);
        courses = (List<Course>) jsonReader.read(filePath, Course.class);
        courses.forEach(System.out::println);
    }
}
