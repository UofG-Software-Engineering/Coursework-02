package uofg.se.group;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uofg.se.group.Application;
import uofg.se.group.constant.Constant;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Requirement;

import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.pojo.entity.Training;
import uofg.se.group.pojo.vo.RequirementVO;
import uofg.se.group.pojo.vo.TrainingVO;
import uofg.se.group.dao.CourseRepo;
import uofg.se.group.service.PersonService;
import uofg.se.group.service.RequirementService;
import uofg.se.group.service.SkillService;
import uofg.se.group.service.TrainingService;

/**
 * @Description Implement test
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class ImplementTest {

    @Resource
    private PersonService personService;
    @Resource
    private RequirementService requirementService;
    @Resource
    private CourseRepo courseRepo;
    @Resource
    private SkillService skillService;
    @Resource
    private TrainingService trainingService;

    @Before
    public void init() {
        initDir();
        initPerson();
        initSkill();
        initStaff();
        initCourse();
        initTraining();
    }

    public void initDir() {
        File file = new File(Constant.DATA_DIR_PATH);
        // Create directory
        if (!file.exists()) {
            file.mkdir();
        }
        // Delete all data
        else  {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                f.delete();
            }
        }
    }

    public void initPerson() {
        List<Person> people = new ArrayList<>() {{
            add(new Person("Chris", RoleEnum.PTT_DIRECTOR.getValue()));
            add(new Person("Mars", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Christina", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Louise", RoleEnum.STAFF.getValue()));
            add(new Person("Gray", RoleEnum.STAFF.getValue()));
        }};
        personService.saveAll(people);
    }

    public void initSkill() {
        List<Skill> skills = new ArrayList<>() {{
            add(new Skill("Java"));
            add(new Skill("JavaScript"));
            add(new Skill("Python"));
        }};
        skillService.saveAll(skills);
    }

    public void initStaff() {
        List<Skill> skills = skillService.findAllByNameLike("Java");
        Person staff = personService.findAllByRole(RoleEnum.STAFF).get(0);
        // Add skills to staff
        personService.addStaffSkill(staff.getId(), skills.stream().map(Skill::getId).collect(Collectors.toList()));
        staff = personService.findOneStaff(staff.getId());
        log.info("Staff: {}", staff);
    }

    public void initCourse() {
        List<Person> courseDirectors = personService.findAllByRole(RoleEnum.COURSE_DIRECTOR);
        List<Course> courses = new ArrayList<>() {{
            add(new Course("2024-1", "Java Programming Design", courseDirectors.get(0).getId()));
            add(new Course("2024-1", "Python Programming Design", courseDirectors.get(1).getId()));
        }};
        courseRepo.saveAll(courses);
    }

    public void initTraining() {
        trainingService.save(Training.builder().name("Training 1").build());
        trainingService.save(Training.builder().name("Training 2").build());
    }

    @Test
    public void addRequirement() {
        // Prepare required data
        Course course = courseRepo.findAll().get(0);
        String courseId = course.getId();
        String courseDirectorId = course.getCourseDirectorId();
        List<String> skillIds = skillService.findAllByNameLike("Java").stream().map(Skill::getId).collect(Collectors.toList());
        // Add requirement
        String requirementId = requirementService.save(
                Requirement.builder()
                        .courseId(courseId)
                        .courseDirectorId(courseDirectorId)
                        .skillIds(skillIds).build());
        // Show result
        RequirementVO requirementVO = requirementService.findOneVO(requirementId);
        log.info("requirementVO: {}", requirementVO);
    }

    @Test
    public void approvalRequirement() {
        addRequirement();
        // Prepare required data
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.PENDING).get(0);
        Person person = personService.findAllByRole(RoleEnum.PTT_DIRECTOR).get(0);
        // Approval requirement
        requirementService.approval(requirement.getId(), person.getId(), RequirementStatusEnum.APPROVED);
        // Show result
        RequirementVO requirementVO = requirementService.findOneVO(requirement.getId());
        log.info("requirementVO: {}", requirementVO);
    }


    @Test
    public void matchSuitableStaff() {
        approvalRequirement();
        // Find suitable staff
        Requirement requirement = requirementService.findAllByStatus(RequirementStatusEnum.APPROVED).get(0);
        List<Person> persons = personService.findAllSuitableStaff(requirement.getSkillIds());
        // Assign staff
        requirementService.assignStaff(requirement.getId(), persons.get(0).getId());
        requirement = requirementService.findOne(requirement.getId());
        // Show result
        RequirementVO requirementVO = requirementService.findOneVO(requirement.getId());
        log.info("requirementVO: {}", requirementVO);
    }

    @Test
    public void addTraining() {
        // Prepare required data
        List<String> staffIds = personService.findAllByRole(RoleEnum.STAFF).stream().map(Person::getId).collect(Collectors.toList());
        Training training = trainingService.findAll().get(0);
        // Add staffs to training
        trainingService.addStaff(training.getId(), staffIds);
        TrainingVO trainingVO = trainingService.findOneVO(training.getId());
        log.info("Training: {}", trainingVO);
    }
}
