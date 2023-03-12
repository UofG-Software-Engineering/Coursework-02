package uofg.se.group.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.pojo.entity.Skill;

/**
 * @Description Skill repository
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
@Component
public class SkillRepo extends BaseRepo<Skill> {

    @Resource
    private StaffSkillRepo staffSkillRepo;
    public SkillRepo() {
        super(EntityEnum.SKILL.getDataSourceFilePath());
    }

    public List<Skill> findAllByStaffId(String staffId) {
        List<String> skillIds = staffSkillRepo.findAllByStaffId(staffId);

        return findAllById(skillIds);
    }

    public List<Skill> findAllByNameLike(String name) {
        return findAll().stream().filter(skill -> skill.getName().contains(name)).collect(Collectors.toList());
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Skill> findAll() {
        return (List<Skill>) jsonReader.read(dataSourceFilePath, Skill.class);
    }
}
