package uofg.se.group.service;

import uofg.se.group.constant.PersonTypeEnum;
import uofg.se.group.constant.RequirementStatusEnum;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.PermissionErrorException;
import uofg.se.group.repo.RequirementRepo;
import uofg.se.group.entity.Requirement;
import uofg.se.group.exception.DataNotFoundException;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class RequirementService extends BaseService<Requirement>{

    private final PTTDirectorService pttDirectorService = PTTDirectorService.getInstance();
    private static RequirementService instance;

    public static RequirementService getInstance() {
        if (instance == null) {
            instance = new RequirementService();
        }
        return instance;
    }

    public void approval(String personId, String requirementId, RequirementStatusEnum requirementStatus) {
        PTTDirector pttDirector = pttDirectorService.findOne(personId);
        PersonTypeEnum personType = pttDirector.getPersonType();
        if (PersonTypeEnum.PTT_DIRECTOR != personType) {
            throw new PermissionErrorException(PersonTypeEnum.PTT_DIRECTOR, "personId");
        }
        Requirement requirement = findOne(requirementId);
        requirement.setStatus(requirementStatus);
        RequirementRepo.save(requirement);
    }

    public Requirement findOne(String requirementID) {
        Requirement requirement = RequirementRepo.findOne(requirementID);
        if (requirement == null) {
            throw new DataNotFoundException(requirementID);
        }

        return requirement;
    }

}
