package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import uofg.se.group.entity.BaseEntity;
import uofg.se.group.exception.DataNotFoundException;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseRepo<Entity extends BaseEntity> {

    // TODO 分离数据层 查询逻辑下放至数据连接层

    private final Set<Entity> entities;

    protected BaseRepo() {
        entities = new HashSet<>();
    }

    public void save(Entity entity) {
        if (null == entity.getId()) {
            entity.setId(UUID.randomUUID().toString());
        }
        entities.add(entity);
    }

    public void saveAll(List<Entity> entities) {
        entities.forEach(this::save);
    }

    public Entity findOne(String id) {
        Entity entity = entities.stream().filter(pttDirector -> pttDirector.getId().equals(id)).findFirst().orElse(null);
        if (null == entity) {
            throw new DataNotFoundException(id);
        }

        return entity;
    }

    public List<Entity> findAll() {
        return List.copyOf(entities);
    }

    public List<Entity> findAll(List<String> ids) {
        return entities.stream().filter(entity -> ids.contains(entity.getId())).collect(Collectors.toList());
    }

    public List<String> findAllId() {
        return entities.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

}
