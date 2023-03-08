package uofg.se.group.service;

import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVO;
import uofg.se.group.dao.RequirementRepo;

/**
 * @Description Teaching requirement service
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
@Component
public class RequirementService extends BaseService<Requirement, RequirementRepo> {

    public String save(Requirement requirement) {
        requirement.setStatus(RequirementStatusEnum.PENDING);
        return repo.save(requirement);
    }

    public void approval(String requirementId, String personId, RequirementStatusEnum status) {
        repo.updateStatus(requirementId, personId, status);
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
