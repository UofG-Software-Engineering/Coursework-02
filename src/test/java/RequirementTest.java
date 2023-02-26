import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.entity.Requirement;
import uofg.se.group.repo.BaseRepo;
import uofg.se.group.repo.PTTDirectorRepo;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.service.PTTDirectorService;
import uofg.se.group.service.RequirementService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

public class RequirementTest {

    private static final RequirementRepo requirementRepo = new RequirementRepo();
    private static final PTTDirectorRepo pttDirectorRepo = new PTTDirectorRepo();
    private final PTTDirectorService pttDirectorService = new PTTDirectorService(pttDirectorRepo);
    private final RequirementService requirementService = new RequirementService(requirementRepo, pttDirectorRepo);

    static {
        List<Requirement> requirements = new ArrayList<>() {{
            add(Requirement.builder().id("1").status(RequirementStatusEnum.PENDING).build());
        }};
        requirementRepo.addAll(requirements);
        List<PTTDirector> pttDirectors = new ArrayList<>() {{
            add(new PTTDirector("1", "Chris", PersonTypeEnum.PTT_DIRECTOR));
        }};
        pttDirectorRepo.addAll(pttDirectors);
    }


    @Test
    public void findOne() {
        Requirement requirement = requirementService.findOne("1");
        System.out.println(requirement);
    }

    @Test
    public void approvalTest() {
        Requirement requirement = requirementService.findOne("1");
        PTTDirector pttDirector = pttDirectorService.findOne("1");
        System.out.println(requirement);
        requirementService.approval(requirement.getId(), pttDirector.getId(), RequirementStatusEnum.APPROVED);
        requirement = requirementService.findOne("1");
        System.out.println(requirement);
    }
}
