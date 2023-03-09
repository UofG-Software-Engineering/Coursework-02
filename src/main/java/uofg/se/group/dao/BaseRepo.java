package uofg.se.group.dao;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import uofg.se.group.pojo.entity.BaseEntity;
import uofg.se.group.util.JsonReader;
import uofg.se.group.util.JsonWriter;

/**
 * @Description Base repository
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
public abstract class BaseRepo<Entity extends BaseEntity> {

    protected final String dataSourceFilePath;
    @Resource
    protected JsonReader jsonReader;
    @Resource
    private JsonWriter jsonWriter;

    protected BaseRepo(String dataSourceFilePath) {
        this.dataSourceFilePath = dataSourceFilePath;
    }

    public String save(Entity entity) {
        // Convert list to map to avoid duplicate
        Map<String, Entity> entities = findAll().stream().collect(Collectors.toMap(BaseEntity::getId, item -> item));
        config(entity);
        entities.put(entity.getId(), entity);
        jsonWriter.write(dataSourceFilePath, entities.values());
        return entity.getId();
    }

    public void saveAll(List<Entity> entities) {
        // Convert list to map to avoid duplicate
        Map<String, Entity> originalEntities = findAll().stream().collect(Collectors.toMap(BaseEntity::getId, item -> item));
        entities.forEach(entity -> {
            config(entity);
            originalEntities.put(entity.getId(), entity);
        });
        jsonWriter.write(dataSourceFilePath, originalEntities.values());
    }

    public Entity findOne(String id) {
        return findAll().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);
    }

    public abstract List<Entity> findAll();

    public List<Entity> findAllById(List<String> ids) {
        List<Entity> entities = findAll();
        return entities.stream().filter(entity -> ids.contains(entity.getId())).collect(Collectors.toList());
    }

    public List<String> findAllId() {
        return findAll().stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    // Config entity before save
    private void config(Entity entity) {
        if (null == entity.getId()) {
            entity.setId(UUID.randomUUID().toString());
        }
        if (null == entity.getCreatedTime()) {
            entity.setCreatedTime(LocalDateTime.now().toString());
        }
        entity.setUpdatedTime(LocalDateTime.now().toString());
    }

}
