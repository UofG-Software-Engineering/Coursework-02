package uofg.se.group.service;

import uofg.se.group.entity.CourseDirector;
import uofg.se.group.entity.PTTDirector;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.repo.CourseDirectorRepo;
import uofg.se.group.repo.PTTDirectorRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class CourseDirectorService extends BaseService<CourseDirector, CourseDirectorRepo>{
    public CourseDirectorService(CourseDirectorRepo repo) {
        super(repo);
    }

    // private static CourseDirectorService instance;
    //
    // public static CourseDirectorService getInstance() {
    //     if (instance == null) {
    //         instance = new CourseDirectorService();
    //     }
    //     return instance;
    // }
    //
    // public CourseDirector findOne(String personId) {
    //     CourseDirector pttDirector = CourseDirectorRepo.findOne(personId);
    //     if (pttDirector == null) {
    //         throw new DataNotFoundException(personId);
    //     }
    //
    //     return pttDirector;
    // }
}
