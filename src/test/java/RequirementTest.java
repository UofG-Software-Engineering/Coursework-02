import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.Person;
import uofg.se.group.entity.Requirement;

import uofg.se.group.repo.CourseDirectorRepo;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.service.CourseDirectorService;
import uofg.se.group.service.PersonService;
import uofg.se.group.service.RequirementService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

@SpringBootTest
public class RequirementTest {

    @Resource
    private RequirementRepo requirementRepo;
    @Resource
    private PersonRepo personRepo;
    @Resource
    private CourseDirectorRepo courseDirectorRepo;
    @Resource
    private PersonService personService;
    @Resource
    private RequirementService requirementService;

    @Before
    public void init() {
        List<Person> people = new ArrayList<>() {{
            add(new Person("1", "Chris", RoleEnum.PTT_DIRECTOR));
        }};
        personRepo.saveAll(people);
        List<Requirement> requirements = new ArrayList<>() {{
            add(Requirement.builder().id("1").status(RequirementStatusEnum.PENDING).build());
        }};
        requirementRepo.saveAll(requirements);
    }

    @Test
    public void add() {
        Requirement requirement = Requirement.builder().courseId("1").build();
        requirementService.add("1", requirement);
        System.out.println(requirement);
    }


    @Test
    public void findOne() {
        Requirement requirement = requirementService.findOne("1");
        System.out.println(requirement);
    }

    @Test
    public void findAll() {
        List<Requirement> requirements = requirementService.findAll();
        System.out.println(requirements);
    }

    @Test
    public void approvalTest() {
        Requirement requirement = requirementService.findOne("1");
        Person person = personService.findOne("1");
        System.out.println(requirement);
        requirementService.approval(requirement.getId(), person.getId(), RequirementStatusEnum.APPROVED);
        requirement = requirementService.findOne("1");
        System.out.println(requirement);
    }
}
