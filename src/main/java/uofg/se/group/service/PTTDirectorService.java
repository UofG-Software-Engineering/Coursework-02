package uofg.se.group.service;

import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.repo.PTTDirectorRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class PTTDirectorService extends BaseService<PTTDirector, PTTDirectorRepo> {

    private static PTTDirectorService instance;

    public PTTDirectorService(PTTDirectorRepo PTTDirectorRepo) {
        super(PTTDirectorRepo);
    }

    public static PTTDirectorService getInstance(PTTDirectorRepo pttDirectorRepo) {
        if (instance == null) {
            instance = new PTTDirectorService(pttDirectorRepo);
        }
        return instance;
    }

}
