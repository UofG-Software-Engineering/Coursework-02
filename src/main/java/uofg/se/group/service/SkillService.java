package uofg.se.group.service;

import uofg.se.group.entity.Skill;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.BaseRepo;
import uofg.se.group.repo.SkillRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class SkillService extends BaseService<Skill, SkillRepo>{
    public SkillService() {
        super(Injector.getInstance(SkillRepo.class));
    }
}
