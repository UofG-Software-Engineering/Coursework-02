package uofg.se.group.service;

import java.util.List;
import javax.annotation.Resource;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVo;
import uofg.se.group.repo.CourseRepo;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.RequirementRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class RequirementService extends BaseService<Requirement, RequirementRepo> {

    @Resource
    private PersonRepo personRepo;
    @Resource
    private CourseRepo courseRepo;

    public String add(Requirement requirement) {
        String courseDirectorId = requirement.getCourseDirectorId();
        if (!personRepo.existsByPersonIdAndRole(courseDirectorId, RoleEnum.COURSE_DIRECTOR)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        if (!courseRepo.existsByCourseIdAndCourseDirectorId(requirement.getCourseId(), courseDirectorId)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        requirement.setStatus(RequirementStatusEnum.PENDING);

        return repo.save(requirement);
    }

    public void approval(String personId, String requirementId, RequirementStatusEnum requirementStatus) {
        assert personRepo.existsByPersonIdAndRole(personId, RoleEnum.PTT_DIRECTOR);
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(requirementStatus);

        repo.save(requirement);
    }

    public void assignStaff(String requirementId, String staffId) {
        Requirement requirement = findOne(requirementId);
        requirement.setStaffId(staffId);

        repo.save(requirement);
    }

    public RequirementVo findOneVo(String requirementId) {
        return repo.findOneVo(requirementId);
    }

    public List<Requirement> findAllByStatus(RequirementStatusEnum status) {
        return repo.findAllByStatus(status);
    }
}
