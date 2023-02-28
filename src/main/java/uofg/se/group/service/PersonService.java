package uofg.se.group.service;

import java.util.List;
import org.springframework.stereotype.Service;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.repo.PersonRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Service
public class PersonService extends BaseService<Person, PersonRepo> {

    public List<Person> findAllByRole(RoleEnum role) {
        return repo.findAllByRole(role);
    }

    public List<Person> findAllSuitableStaff(List<String> skillIds) {
        return repo.findAllByRoleAndSkillId(RoleEnum.STAFF, skillIds);
    }
}
