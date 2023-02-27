package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import uofg.se.group.entity.BaseEntity;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseRepo<T extends BaseEntity> {

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

    public List<T> findAll() {
        return List.copyOf(entities);
    }

    public List<T> finAll(List<String> ids) {
        return entities.stream().filter(entity -> ids.contains(entity.getId())).collect(Collectors.toList());
    }

    public List<String> findAllId() {
        return entities.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }
    public void save(T entity) {
        if (null == entity.getId()) {
            entity.setId(UUID.randomUUID().toString());
        }
        entities.add(entity);
    }
}
