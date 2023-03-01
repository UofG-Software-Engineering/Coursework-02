package uofg.se.group.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.Course;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.entity.Skill;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Component
public class SkillRepo extends BaseRepo<Skill> {

    @Resource
    private StaffSkillRepo staffSkillRepo;
    public SkillRepo() {
        super("data/skill.json");
    }

    public List<Skill> findAllByStaffId(String staffId) {
        List<String> skillIds = staffSkillRepo.findAllByStaffId(staffId);

        return findAllById(skillIds);
    }

    @Override
    public List<Skill> findAll() {
        return (List<Skill>) jsonReader.read(dataSourceFilePath, Skill.class);
    }
}
