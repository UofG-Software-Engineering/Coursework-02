package uofg.se.group.repo;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVO;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class RequirementRepo extends BaseRepo<Requirement> {

    @Resource
    private CourseRepo courseRepo;
    @Resource
    private PersonRepo personRepo;
    @Resource
    private SkillRepo skillRepo;

    public RequirementRepo() {
        super(EntityEnum.Requirement.getDataSourceFilePath());
    }

    public String add(Requirement requirement) {
        String courseDirectorId = requirement.getCourseDirectorId();
        if (!personRepo.existsByPersonIdAndRole(courseDirectorId, RoleEnum.COURSE_DIRECTOR)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        if (!courseRepo.existsByCourseIdAndCourseDirectorId(requirement.getCourseId(), courseDirectorId)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        requirement.setStatus(RequirementStatusEnum.PENDING);

        return save(requirement);
    }
    public RequirementVO findOneVO(String requirementId) {
        Requirement requirement = findOne(requirementId);
        return RequirementVO.builder().id(requirement.getId()).course(courseRepo.findOne(requirement.getCourseId()))
                .CourseDirector(personRepo.findOne(requirement.getCourseDirectorId()))
                .staff(personRepo.findOne(requirement.getStaffId()))
                .skills(skillRepo.findAllById(requirement.getSkillIds())).status(requirement.getStatus()).build();
    }

    public List<Requirement> findAllByStatus(RequirementStatusEnum status) {
        return findAll().stream().filter(requirement -> requirement.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public void updateStaffId(String requirementId, String staffId) {
        assert personRepo.existsByPersonIdAndRole(staffId, RoleEnum.STAFF);
        Requirement requirement = findOne(requirementId);
        requirement.setStaffId(staffId);

        save(requirement);
    }

    public void updateStatus(String personId, String requirementId, RequirementStatusEnum status) {
        assert personRepo.existsByPersonIdAndRole(personId, RoleEnum.PTT_DIRECTOR);
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(status);

        save(requirement);
    }
    @Override
    public List<Requirement> findAll() {
        return (List<Requirement>) jsonReader.read(dataSourceFilePath, Requirement.class);
    }
}
