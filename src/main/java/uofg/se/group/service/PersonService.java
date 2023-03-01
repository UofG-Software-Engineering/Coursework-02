package uofg.se.group.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Skill;
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

    public String addStaffSkill(String staffId, String skillId) {
        return staffSkillRepo.save(new StaffSkill(staffId, skillId));
    }

    public Staff findStaff(String staffId) {
        return repo.findStaff(staffId);
    }

    public List<Person> findAllByRole(RoleEnum role) {
        return repo.findAllByRole(role);
    }

    public List<Person> findAllSuitableStaff(List<String> skillIds) {
        return repo.findAllByRoleAndSkillId(RoleEnum.STAFF, skillIds);
    }
}
