import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.entity.Requirement;
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

    private final PTTDirectorService pttDirectorService = PTTDirectorService.getInstance();
    private final RequirementService requirementService = RequirementService.getInstance();

    static {
        List<Requirement> requirements = new ArrayList<>() {{
            add(new Requirement("1", RequirementStatusEnum.PENDING));
        }};
        RequirementRepo.create(requirements);
        List<PTTDirector> pttDirectors = new ArrayList<>() {{
            add(new PTTDirector("1", "Chris", PersonTypeEnum.PTT_DIRECTOR));
        }};
        PTTDirectorRepo.addAll(pttDirectors);
    }


    @Test
    public void test() {
        Requirement requirement = RequirementRepo.findOne("1");
        System.out.println(requirement);
    }

    @Test
    public void approvalTest() {
        Requirement requirement = RequirementRepo.findOne("1");
        PTTDirector pttDirector = PTTDirectorRepo.findOne("1");
        System.out.println(requirement);
        requirementService.approval(requirement.getId(), pttDirector.getId(), RequirementStatusEnum.APPROVED);
        requirement = RequirementRepo.findOne("1");
        System.out.println(requirement);
    }
}
