package uofg.se.group.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import uofg.se.group.entity.Person;
import uofg.se.group.entity.StaffSkill;
import uofg.se.group.repo.PersonRepo;
import uofg.se.group.repo.StaffSkillRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/27
 */
public class StaffSkillService extends BaseService<StaffSkill, StaffSkillRepo>{

    @Resource
    private PersonRepo personRepo;
    // TODO 复杂查询下放至 repo
    public List<Person> findAllSuitableStaff(List<String> skillIds) {
        List<StaffSkill> staffSkills = repo.findAll();
        List<String> staffIds = personRepo.findAllId();
        List<String> staffIdsFiltered = staffIds.stream().filter(staffId -> {
            List<String> staffSkillFiltered =
                    staffSkills.stream().filter(staffSkill -> staffSkill.getStaffId().equals(staffId))
                            .map(StaffSkill::getSkillId).collect(Collectors.toList());
            return new HashSet<>(staffSkillFiltered).containsAll(skillIds);

        }).collect(Collectors.toList());

        return personRepo.findAll(staffIdsFiltered);
    }
}
