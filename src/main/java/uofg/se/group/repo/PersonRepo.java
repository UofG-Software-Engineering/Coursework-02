package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.pojo.entity.Staff;
import uofg.se.group.pojo.entity.StaffSkill;
import uofg.se.group.util.PersonMapper;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class PersonRepo extends BaseRepo<Person> {

    @Resource
    private StaffSkillRepo staffSkillRepo;
    @Resource SkillRepo skillRepo;
    public PersonRepo() {
        super(EntityEnum.PERSON.getDataSourceFilePath());
    }

    public boolean existsByPersonIdAndRole(String personId, RoleEnum role) {
        return findAll().stream().anyMatch(person -> person.getId().equals(personId) && StringUtils.equals(person.getRole(), role.getValue()));
    }

    public Staff findOneStaff(String staffId) {
        Person person = findOne(staffId);
        List<String> skillIds = staffSkillRepo.findAllByStaffId(staffId);
        List<Skill> skills = skillRepo.findAllById(skillIds);
        Staff staff = PersonMapper.INSTANCE.Person2Staff(person);
        staff.setSkills(skills);

        return staff;
    }

    public List<Person> findAllByRole(RoleEnum role) {
        return findAll().stream()
                .filter(person -> StringUtils.equals(person.getRole(), role.getValue()))
                .collect(Collectors.toList());
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

    @Override
    public List<Person> findAll() {
        return (List<Person>) jsonReader.read(dataSourceFilePath, Person.class);
    }
}
