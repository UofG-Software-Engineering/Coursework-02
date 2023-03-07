import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uofg.se.group.Application;
import uofg.se.group.constant.Constant;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Requirement;

import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.pojo.vo.RequirementVO;
import uofg.se.group.repo.CourseRepo;
import uofg.se.group.service.PersonService;
import uofg.se.group.service.RequirementService;
import uofg.se.group.service.SkillService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@Slf4j
public class RequirementTest {

    @Resource
    private PersonService personService;
    @Resource
    private RequirementService requirementService;
    @Resource
    private CourseRepo courseRepo;
    @Resource
    private SkillService skillService;

    @Before
    public void init() {
        // Delete all data
        File file = new File(Constant.DATA_DIR_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        else  {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                f.delete();
            }
        }
        List<Person> people = new ArrayList<>() {{
            add(new Person("Chris", RoleEnum.PTT_DIRECTOR.getValue()));
            add(new Person("Mars", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Christina", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Louise", RoleEnum.STAFF.getValue()));
            add(new Person("Gray", RoleEnum.STAFF.getValue()));
        }};
        personService.saveAll(people);
        List<Skill> skills = new ArrayList<>() {{
            add(new Skill("Java"));
            add(new Skill("JavaScript"));
            add(new Skill("Python"));
        }};
        skillService.saveAll(skills);
        List<Person> courseDirectors = personService.findAllByRole(RoleEnum.COURSE_DIRECTOR);
        List<Course> courses = new ArrayList<>() {{
            add(new Course("2024-1", "Java Programming Design", courseDirectors.get(0).getId()));
            add(new Course("2024-1", "Python Programming Design", courseDirectors.get(1).getId()));
        }};
        courseRepo.saveAll(courses);

    }

    @Test
    public void addRequirement() {
        Course course = courseRepo.findAll().get(0);
        String courseId = course.getId();
        String courseDirectorId = course.getCourseDirectorId();
        String skill1Id  = skillService.findAll().get(0).getId();
        String skill2Id = skillService.save(new Skill("Web Development"));
        String requirementId = requirementService.save(
                Requirement.builder()
                        .courseId(courseId)
                        .courseDirectorId(courseDirectorId)
                        .skillIds(new ArrayList<>() {{
                    add(skill1Id);
                    add(skill2Id);
                }}).build());
        RequirementVO requirementVO = requirementService.findOneVO(requirementId);
        log.info("requirementVO: {}", requirementVO);
    }

    @Test
    public void approvalRequirement() {
        addRequirement();
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.PENDING).get(0);
        Person person = personService.findAllByRole(RoleEnum.PTT_DIRECTOR).get(0);

        requirementService.approval(requirement.getId(), person.getId(), RequirementStatusEnum.APPROVED);
        requirement = requirementService.findOne(requirement.getId());
        System.out.println(requirement);
    }


    @Test
    public void addStaffSkill() {
        List<Skill> skills = skillService.findAllByNameLike("Java");
        skills.addAll(skillService.findAllByNameLike("Web Development"));
        Person staff = personService.findAllByRole(RoleEnum.STAFF).get(0);
        personService.addStaffSkill(staff.getId(), skills.stream().map(Skill::getId).collect(Collectors.toList()));
        staff = personService.findOneStaff(staff.getId());
        log.info("staff: {}", staff);
    }

    @Test
    public void assignSuitableStaff() {
        approvalRequirement();
        addStaffSkill();
        // find suitable staff
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.APPROVED).get(0);
        List<Person> persons = personService.findAllSuitableStaff(requirement.getSkillIds());
        requirementService.assignStaff(requirement.getId(), persons.get(0).getId());
        requirement = requirementService.findOne(requirement.getId());
        RequirementVO requirementVO = requirementService.findOneVO(requirement.getId());
        log.info("requirementVO: {}", requirementVO);
    }
}
