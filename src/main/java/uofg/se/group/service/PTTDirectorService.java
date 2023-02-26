package uofg.se.group.service;

import javax.inject.Singleton;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.PTTDirectorRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Singleton
public class PTTDirectorService extends BaseService<PTTDirector, PTTDirectorRepo> {

    public PTTDirectorService() {
        super(Injector.getInstance(PTTDirectorRepo.class));
    }
}
