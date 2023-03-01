package uofg.se.group.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.pojo.entity.StaffSkill;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/27
 */
@Component
public class StaffSkillRepo extends BaseRepo<StaffSkill> {
    public StaffSkillRepo() {
        super("data/staffSkill.json");
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
