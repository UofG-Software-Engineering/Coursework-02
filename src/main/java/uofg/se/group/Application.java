package uofg.se.group;

import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import uofg.se.group.dao.CourseRepo;
import uofg.se.group.dao.PersonRepo;
import uofg.se.group.dao.SkillRepo;
import uofg.se.group.dao.StaffSkillRepo;
import uofg.se.group.service.PersonService;

/**
 * @Description No web application runner
 * @Author Xiaohui Yu
 * @Date 2022/5/6
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class)
                // Set no web application type
                .web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(ApplicationArguments args) {

    }
}
