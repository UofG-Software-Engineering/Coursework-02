package uofg.se.group.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.pojo.entity.StaffSkill;

/**
 * @Description Staff-Skill repository
 * @Author Xiaohui Yu
 * @Date 2023/2/27
 */
@Component
public class StaffSkillRepo extends BaseRepo<StaffSkill> {
    public StaffSkillRepo() {
        super(EntityEnum.STAFF_SKILL.getDataSourceFilePath());
    }

    public void addStaffSkill(String staffId, List<String> skillIds) {
        skillIds.forEach(skillId -> save(new StaffSkill(staffId, skillId)));
    }
    public List<String> findAllByStaffId(String staffId) {
        return findAll().stream().filter(staffSkill -> staffSkill.getStaffId().equals(staffId)).map(StaffSkill::getSkillId).collect(
                Collectors.toList());
    }

    @Override
    public List<StaffSkill> findAll() {
        return (List<StaffSkill>) jsonReader.read(dataSourceFilePath, StaffSkill.class);
    }
}
