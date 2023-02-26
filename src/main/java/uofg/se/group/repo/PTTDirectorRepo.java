package uofg.se.group.repo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uofg.se.group.entity.PTTDirector;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class PTTDirectorRepo extends BaseRepo<PTTDirector> {

    private static final Set<PTTDirector> PTTDirectors;

    static {
        PTTDirectors = new HashSet<>();
    }

    public static void addAll(List<PTTDirector> pttDirectors) {
        PTTDirectors.addAll(pttDirectors);
    }

    public static PTTDirector findOne(String id) {
        return PTTDirectors.stream().filter(pttDirector -> pttDirector.getId().equals(id)).findFirst().orElse(null);
    }
}
