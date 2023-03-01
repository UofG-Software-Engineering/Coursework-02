package uofg.se.group;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
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

    // TODO 测试整理
    @Override
    public void run(ApplicationArguments args) {
        // Delete all data
        File file = new File("data");
        if (!file.exists()) {
            file.mkdir();
        }
        else  {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                f.delete();
            }
        }
        List<Person> people = new ArrayList<>() {{
            add(new Person("Chris", RoleEnum.PTT_DIRECTOR));
            add(new Person("Mars", RoleEnum.COURSE_DIRECTOR));
            add(new Person("Christina", RoleEnum.COURSE_DIRECTOR));
            add(new Person("Louise", RoleEnum.STAFF));
            add(new Person("Gray", RoleEnum.STAFF));
        }};
        personRepo.saveAll(people);
        List<Skill> skills = new ArrayList<>() {{
            add(new Skill("Java"));
            add(new Skill("Python"));
        }};
        skillRepo.saveAll(skills);
        skills = skillRepo.findAll();
        people = personService.findAll();
        people.forEach(System.out::println);
        List<Person> courseDirectors = personService.findAllByRole(RoleEnum.COURSE_DIRECTOR);
        List<Course> courses = new ArrayList<>() {{
            add(new Course("2024-1", "Java Programming Design", courseDirectors.get(0).getId()));
            add(new Course("2024-1", "Python Programming Design", courseDirectors.get(1).getId()));
        }};
        courseRepo.saveAll(courses);
        List<Person> staffs = personService.findAllByRole(RoleEnum.STAFF);
        personService.addStaffSkill(staffs.get(0).getId(), skills.get(0).getId());
        personService.addStaffSkill(staffs.get(1).getId(), skills.get(1).getId());
        Staff staff1 = personService.findStaff(staffs.get(0).getId());
        Staff staff2 = personService.findStaff(staffs.get(1).getId());
        System.out.println(staff1);
        System.out.println(staff2);

    }
}
