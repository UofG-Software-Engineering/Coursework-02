package uofg.se.group;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.pojo.entity.Staff;
import uofg.se.group.repo.CourseRepo;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.SkillRepo;
import uofg.se.group.repo.StaffSkillRepo;
import uofg.se.group.service.PersonService;

/**
 * @Author Chris
 * @Date 2022/5/6
 * @Description 非 Web 项目启动类
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

    @Resource
    private PersonRepo personRepo;
    @Resource
    private SkillRepo skillRepo;
    @Resource
    private StaffSkillRepo staffSkillRepo;
    @Resource
    private CourseRepo courseRepo;
    @Resource
    private PersonService personService;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class)
                // 指定非 web 模式
                .web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(ApplicationArguments args) {

    }
}
