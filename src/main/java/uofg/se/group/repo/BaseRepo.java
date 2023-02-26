package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uofg.se.group.entity.BaseEntity;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseRepo<T extends BaseEntity> {

    protected BaseRepo<T> instance;

    private final Set<T> entities;

    protected BaseRepo() {
        entities = new HashSet<>();
    }

    public void addAll(List<T> entities) {
        this.entities.addAll(entities);
    }

    public T findOne(String id) {
        return entities.stream().filter(pttDirector -> pttDirector.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(T entity) {
        entities.add(entity);
    }
}
