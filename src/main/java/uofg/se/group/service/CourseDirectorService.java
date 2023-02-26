package uofg.se.group.service;

import javax.inject.Singleton;
import uofg.se.group.entity.CourseDirector;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.CourseDirectorRepo;
import uofg.se.group.repo.PTTDirectorRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Singleton
public class CourseDirectorService extends BaseService<CourseDirector, CourseDirectorRepo>{

    public CourseDirectorService() {
        super(Injector.getInstance(CourseDirectorRepo.class));
    }

}
