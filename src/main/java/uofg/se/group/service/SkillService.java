package uofg.se.group.service;

import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.Skill;
import uofg.se.group.dao.SkillRepo;

/**
 * @Description Skill service
 * @Author Xiaohui Yu
 * @Date 2023/3/1
 */
@Component
public class SkillService extends BaseService<Skill, SkillRepo>{

    public List<Skill> findAllByNameLike(String name) {
        return repo.findAllByNameLike(name);
    }
}
