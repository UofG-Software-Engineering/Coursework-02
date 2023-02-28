package uofg.se.group.repo;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class RequirementRepo extends BaseRepo<Requirement> {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private SkillRepo skillRepo;

    public RequirementVo findOneVo(String requirementId) {
        Requirement requirement = findOne(requirementId);
        return RequirementVo.builder().id(requirement.getId()).course(courseRepo.findOne(requirement.getCourseId()))
                .CourseDirector(personRepo.findOne(requirement.getCourseDirectorId()))
                .staff(personRepo.findOne(requirement.getStaffId()))
                .skills(skillRepo.findAllById(requirement.getSkillIds())).status(requirement.getStatus()).build();
    }

    public List<Requirement> findAllByStatus(RequirementStatusEnum status) {
        return findAll().stream().filter(requirement -> requirement.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
