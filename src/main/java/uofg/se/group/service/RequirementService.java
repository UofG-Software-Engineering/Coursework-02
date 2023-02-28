package uofg.se.group.service;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.Course;
import uofg.se.group.entity.Person;
import uofg.se.group.entity.Requirement;
import uofg.se.group.entity.Skill;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.repo.CourseRepo;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.RequirementRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class RequirementService extends BaseService<Requirement, RequirementRepo>{

    @Resource
    private PersonRepo personRepo;
    @Resource
    private CourseRepo courseRepo;

    // TODO 复杂查询下放至 repo
    public void add(String personId, Requirement requirement) {
        Person courseDirector = personRepo.findOne(personId);
        String courseDirectorId = courseDirector.getId();
        if (RoleEnum.COURSE_DIRECTOR != courseDirector.getRole()) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        Course course = courseRepo.findOne(requirement.getCourseId());
        if (!StringUtils.equals(courseDirector.getId(), course.getCourseDirectorId())) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, personId);
        }
        requirement.setCourseDirectorId(personId);
        requirement.setStatus(RequirementStatusEnum.PENDING);
        repo.save(requirement);
    }

    public void approval(String personId, String requirementId, RequirementStatusEnum requirementStatus) {
        Person person = personRepo.findOne(personId);
        RoleEnum personType = person.getRole();
        if (RoleEnum.PTT_DIRECTOR != personType) {
            throw new PermissionErrorException(RoleEnum.PTT_DIRECTOR, "personId");
        }
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(requirementStatus);
        repo.save(requirement);
    }

    public List<Person> findStaff(String requirementId) {
        Requirement requirement = findOne(requirementId);
        List<Skill> skills = requirement.getSkills();
        return null;
    }
}
