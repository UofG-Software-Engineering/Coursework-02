package uofg.se.group.service;

import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.repo.PTTDirectorRepo;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.entity.Requirement;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class RequirementService extends BaseService<Requirement, RequirementRepo>{

    private final PTTDirectorRepo pttDirectorRepo;
    private static RequirementService instance;


    public RequirementService(RequirementRepo requirementRepo, PTTDirectorRepo pttDirectorRepo) {
        super(requirementRepo);
        this.pttDirectorRepo = pttDirectorRepo;
    }

    public void add(String personId, Requirement requirement) {
        PTTDirector pttDirector = pttDirectorRepo.findOne(personId);
        PersonTypeEnum personType = pttDirector.getPersonType();
        if (PersonTypeEnum.COURSE_DIRECTOR != personType) {
            throw new PermissionErrorException(PersonTypeEnum.COURSE_DIRECTOR, "personId");
        }
        repo.save(requirement);
    }
    public void approval(String personId, String requirementId, RequirementStatusEnum requirementStatus) {
        PTTDirector pttDirector = pttDirectorRepo.findOne(personId);
        PersonTypeEnum personType = pttDirector.getPersonType();
        if (PersonTypeEnum.PTT_DIRECTOR != personType) {
            throw new PermissionErrorException(PersonTypeEnum.PTT_DIRECTOR, "personId");
        }
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(requirementStatus);
        repo.save(requirement);
    }

    // public Requirement findOne(String requirementID) {
    //     Requirement requirement = RequirementRepo.findOne(requirementID);
    //     if (requirement == null) {
    //         throw new DataNotFoundException(requirementID);
    //     }
    //
    //     return requirement;
    // }

}
