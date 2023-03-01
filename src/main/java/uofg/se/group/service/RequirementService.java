package uofg.se.group.service;

import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVO;
import uofg.se.group.repo.RequirementRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class RequirementService extends BaseService<Requirement, RequirementRepo> {

    public String add(Requirement requirement) {
        return repo.add(requirement);
    }

    public void approval(String personId, String requirementId, RequirementStatusEnum status) {
        repo.updateStatus(personId, requirementId, status);
    }

    public void assignStaff(String requirementId, String staffId) {
        repo.updateStaffId(requirementId, staffId);
    }

    public RequirementVO findOneVO(String requirementId) {
        return repo.findOneVO(requirementId);
    }

    public List<Requirement> findAllByStatus(RequirementStatusEnum status) {
        return repo.findAllByStatus(status);
    }
}
