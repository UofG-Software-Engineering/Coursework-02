package uofg.se.group.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Staff;
import uofg.se.group.pojo.entity.StaffSkill;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.SkillRepo;
import uofg.se.group.repo.StaffSkillRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Service
public class PersonService extends BaseService<Person, PersonRepo> {

    @Resource
    private StaffSkillRepo staffSkillRepo;
    @Resource
    private SkillRepo skillRepo;

    public void addStaffSkill(String staffId, List<String> skillIds) {
        staffSkillRepo.addStaffSkill(staffId, skillIds);
    }

    public Staff findOneStaff(String staffId) {
        return repo.findOneStaff(staffId);
    }

    public List<Person> findAllByRole(RoleEnum role) {
        return repo.findAllByRole(role);
    }

    public List<Person> findAllSuitableStaff(List<String> skillIds) {
        return repo.findAllByRoleAndSkillId(RoleEnum.STAFF, skillIds);
    }
}
