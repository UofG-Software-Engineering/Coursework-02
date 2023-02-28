package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.StaffSkill;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class PersonRepo extends BaseRepo<Person> {

    @Autowired
    private StaffSkillRepo staffSkillRepo;

    public boolean existsByPersonIdAndRole(String personId, RoleEnum role) {
        boolean isExisted =
                findAll().stream().anyMatch(person -> person.getId().equals(personId) && person.getRole() == role);
        if (!isExisted) {
            throw new PermissionErrorException(role, personId);
        }
        return true;
    }

    public List<Person> findAllByRole(RoleEnum role) {
        return findAll().stream().filter(person -> person.getRole() == role).collect(Collectors.toList());
    }

    public List<Person> findAllByRoleAndSkillId(RoleEnum role, List<String> skillIds) {
        List<Person> persons = findAllByRole(role);
        List<StaffSkill> staffSkills = staffSkillRepo.findAll();
        persons = persons.stream().filter(person -> {
            List<String> staffSkillFiltered =
                    staffSkills.stream().filter(staffSkill -> staffSkill.getStaffId().equals(person.getId()))
                            .map(StaffSkill::getSkillId).collect(Collectors.toList());
            return new HashSet<>(staffSkillFiltered).containsAll(skillIds);
        }).collect(Collectors.toList());
        return persons;
    }
}
