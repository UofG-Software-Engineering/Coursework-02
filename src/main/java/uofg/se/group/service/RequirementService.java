package uofg.se.group.service;

import java.util.List;
import java.util.UUID;
import javax.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.CourseDirector;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.entity.Staff;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.inject.Autowired;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.entity.Requirement;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Singleton
public class RequirementService extends BaseService<Requirement, RequirementRepo>{

    private final PTTDirectorService pttDirectorService = Injector.getInstance(PTTDirectorService.class);
    private final CourseDirectorService courseDirectorService = Injector.getInstance(CourseDirectorService.class);


    public RequirementService() {
        super(Injector.getInstance(RequirementRepo.class));
    }

    public void add(String personId, Requirement requirement) {
        CourseDirector courseDirector = courseDirectorService.findOne(personId);
        if (PersonTypeEnum.COURSE_DIRECTOR != courseDirector.getPersonType()) {
            throw new PermissionErrorException(PersonTypeEnum.COURSE_DIRECTOR, "personId");
        }
        if (!StringUtils.equals(courseDirector.getCourseId(), requirement.getCourseId())) {
            throw new PermissionErrorException(PersonTypeEnum.COURSE_DIRECTOR, personId);
        }
        requirement.setId(UUID.randomUUID().toString());
        requirement.setCourseDirectorId(personId);
        requirement.setStatus(RequirementStatusEnum.PENDING);
        repo.save(requirement);
    }

    public void approval(String personId, String requirementId, RequirementStatusEnum requirementStatus) {
        PTTDirector pttDirector = pttDirectorService.findOne(personId);
        PersonTypeEnum personType = pttDirector.getPersonType();
        if (PersonTypeEnum.PTT_DIRECTOR != personType) {
            throw new PermissionErrorException(PersonTypeEnum.PTT_DIRECTOR, "personId");
        }
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(requirementStatus);
        repo.save(requirement);
    }

    public List<Staff> findStaff(String requirementId) {
        Requirement requirement = findOne(requirementId);
        return null;
    }
}
