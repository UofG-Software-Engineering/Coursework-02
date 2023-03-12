package uofg.se.group.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVO;
import uofg.se.group.util.RequirementMapper;

/**
 * @Description Teaching requirement repository
 * @Author Xiaohui Yu
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

    public String save(Requirement requirement) {
        String courseDirectorId = requirement.getCourseDirectorId();
        if (!personRepo.existsByPersonIdAndRole(courseDirectorId, RoleEnum.COURSE_DIRECTOR)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        if (!courseRepo.existsByCourseIdAndCourseDirectorId(requirement.getCourseId(), courseDirectorId)) {
            throw new PermissionErrorException(RoleEnum.COURSE_DIRECTOR, courseDirectorId);
        }
        // Init status
        if (null == requirement.getStatus()) {
            requirement.setStatus(RequirementStatusEnum.PENDING);
        }

        return super.save(requirement);
    }
    public RequirementVO findOneVO(String requirementId) {
        Requirement requirement = findOne(requirementId);
        RequirementVO requirementVO = RequirementMapper.INSTANCE.Entity2VO(requirement);
        requirementVO.setCourse(courseRepo.findOne(requirement.getCourseId()));
        requirementVO.setCourseDirector(personRepo.findOne(requirement.getCourseDirectorId()));
        requirementVO.setStaff(personRepo.findOne(requirement.getStaffId()));
        requirementVO.setSkills(skillRepo.findAllById(requirement.getSkillIds()));

        return requirementVO;
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

    public void updateStatus(String requirementId, String personId, RequirementStatusEnum status) {
        assert personRepo.existsByPersonIdAndRole(personId, RoleEnum.PTT_DIRECTOR);
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(status);

        save(requirement);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Requirement> findAll() {
        return (List<Requirement>) jsonReader.read(dataSourceFilePath, Requirement.class);
    }
}
