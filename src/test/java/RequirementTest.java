import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uofg.se.group.Application;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Requirement;

import uofg.se.group.pojo.vo.RequirementVo;
import uofg.se.group.service.PersonService;
import uofg.se.group.service.RequirementService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
public class RequirementTest {

    @Resource
    private PersonService personService;
    @Resource
    private RequirementService requirementService;

    // TODO Requirement 测试重构

    @Test
    public void addAndFind() {
        String requirementId = requirementService.add(
                Requirement.builder().courseId("1").courseDirectorId("1").skillIds(new ArrayList<>() {{
                    add("1");
                    add("2");
                }}).build());
        Requirement requirement = requirementService.findOne(requirementId);
        System.out.println(requirement);
    }

    @Test
    public void findAll() {
        List<Requirement> requirements = requirementService.findAll();
        System.out.println(requirements);
    }

    @Test
    public void approval() {
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.PENDING).get(0);
        Person person = personService.findAllByRole(RoleEnum.PTT_DIRECTOR).get(0);
        requirementService.approval(requirement.getId(), person.getId(), RequirementStatusEnum.APPROVED);
        requirement = requirementService.findOne(requirement.getId());
        System.out.println(requirement);
    }

    @Test
    public void findSuitableStaff() {
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.APPROVED).get(0);
        List<Person> persons = personService.findAllSuitableStaff(requirement.getSkillIds());
        requirementService.assignStaff(requirement.getId(), persons.get(0).getId());
        requirement = requirementService.findOne(requirement.getId());
        System.out.println(requirement);
        RequirementVo requirementVo = requirementService.findOneVo(requirement.getId());
        System.out.println(requirementVo);
    }
}
