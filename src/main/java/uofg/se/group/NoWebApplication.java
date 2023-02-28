package uofg.se.group;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.entity.Person;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.service.PersonService;

/**
 * @Author Chris
 * @Date 2022/5/6
 * @Description 非 Web 项目启动类
 */
@SpringBootApplication
public class NoWebApplication implements ApplicationRunner {

    @Resource
    private PersonService personService;
    @Resource
    private PersonRepo personRepo;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(NoWebApplication.class)
                // 指定非 web 模式
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Person> people = new ArrayList<>() {{
            add(new Person("Chris", RoleEnum.PTT_DIRECTOR));
        }};
        personRepo.saveAll(people);
       personService.findAll().forEach(System.out::println);
    }
}
