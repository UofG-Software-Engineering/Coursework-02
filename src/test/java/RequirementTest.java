import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.CourseDirector;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.entity.Requirement;
import uofg.se.group.inject.Autowired;
import uofg.se.group.inject.Container;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.BaseRepo;
import uofg.se.group.repo.CourseDirectorRepo;
import uofg.se.group.repo.PTTDirectorRepo;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.service.CourseDirectorService;
import uofg.se.group.service.PTTDirectorService;
import uofg.se.group.service.RequirementService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

public class RequirementTest {

    private static final Container container = new Container();
    private static final RequirementRepo requirementRepo = Injector.getInstance(RequirementRepo.class);
    private static final PTTDirectorRepo pttDirectorRepo = Injector.getInstance(PTTDirectorRepo.class);
    private static final CourseDirectorRepo courseDirectorRepo = Injector.getInstance(CourseDirectorRepo.class);
    private final PTTDirectorService pttDirectorService = Injector.getInstance(PTTDirectorService.class);
    private final CourseDirectorService courseDirectorService = Injector.getInstance(CourseDirectorService.class);
    private static final RequirementService requirementService = Injector.getInstance(RequirementService.class);;
    // private final RequirementService requirementService = new RequirementService(requirementRepo, pttDirectorService, courseDirectorService);

    static {
        container.init();
        List<PTTDirector> pttDirectors = new ArrayList<>() {{
            add(new PTTDirector("1", "Chris", PersonTypeEnum.PTT_DIRECTOR));
        }};
        pttDirectorRepo.addAll(pttDirectors);
        List<CourseDirector> courseDirectors = new ArrayList<>() {{
            add(new CourseDirector("1", "Chris", PersonTypeEnum.COURSE_DIRECTOR, "1"));
        }};
        courseDirectorRepo.addAll(courseDirectors);
        List<Requirement> requirements = new ArrayList<>() {{
            add(Requirement.builder().id("1").status(RequirementStatusEnum.PENDING).build());
        }};
        requirementRepo.addAll(requirements);
    }

    @Test
    public void add() {
        Requirement requirement = Requirement.builder().courseId("1").build();
        requirementService.add("1", requirement);
        System.out.println(requirement);
    }


    @Test
    public void findOne() {
        Requirement requirement = requirementService.findOne("1");
        System.out.println(requirement);
    }

    @Test
    public void findAll() {
        List<Requirement> requirements = requirementService.findAll();
        System.out.println(requirements);
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
