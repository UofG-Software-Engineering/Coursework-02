package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uofg.se.group.entity.Requirement;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class RequirementRepo extends BaseRepo<Requirement>{

    private static final Set<Requirement> REQUIREMENTS;

    static {
        REQUIREMENTS = new HashSet<>();
    }

    public static void create(List<Requirement> Requirements) {
        REQUIREMENTS.addAll(Requirements);
    }

    public static Requirement findOne(String requirementID) {
        return REQUIREMENTS.stream().filter(Requirement -> Requirement.getId().equals(requirementID)).findFirst().orElse(null);
    }

    public synchronized static void save(Requirement requirement) {
        REQUIREMENTS.add(requirement);
    }
}
