package uofg.se.group.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import uofg.se.group.entity.Staff;
import uofg.se.group.entity.StaffSkill;
import uofg.se.group.inject.Autowired;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.StaffSkillRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/27
 */
public class StaffSkillService extends BaseService<StaffSkill, StaffSkillRepo>{

    private final StaffService staffService = Injector.getInstance(StaffService.class);
    public StaffSkillService() {
        super(Injector.getInstance(StaffSkillRepo.class));
    }

    public List<Staff> findAllSuitableStaff(List<String> skillIds) {
        List<StaffSkill> staffSkills = repo.findAll();
        List<String> staffIds = staffService.findAllId();
        List<String> staffIdsFiltered = staffIds.stream().filter(staffId -> {
            List<String> staffSkillFiltered =
                    staffSkills.stream().filter(staffSkill -> staffSkill.getStaffId().equals(staffId))
                            .map(StaffSkill::getSkillId).collect(Collectors.toList());
            return new HashSet<>(staffSkillFiltered).containsAll(skillIds);

        }).collect(Collectors.toList());

        return staffService.findAll(staffIdsFiltered);
    }
}
