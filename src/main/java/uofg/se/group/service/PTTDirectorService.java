package uofg.se.group.service;

import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.repo.PTTDirectorRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class PTTDirectorService extends BaseService<PTTDirector> {

    private static PTTDirectorService instance;

    public static PTTDirectorService getInstance() {
        if (instance == null) {
            instance = new PTTDirectorService();
        }
        return instance;
    }

    public PTTDirector findOne(String personId) {
        PTTDirector pttDirector = PTTDirectorRepo.findOne(personId);
        if (pttDirector == null) {
            throw new DataNotFoundException(personId);
        }

        return pttDirector;
    }
}
