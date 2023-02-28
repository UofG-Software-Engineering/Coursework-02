package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import uofg.se.group.pojo.entity.BaseEntity;
import uofg.se.group.util.JsonReader;
import uofg.se.group.util.JsonWriter;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseRepo<Entity extends BaseEntity> {

    protected String dataSourceFilePath;
    @Autowired
    private JsonReader<Entity> jsonReader;
    @Autowired
    private JsonWriter jsonWriter;

    protected BaseRepo() {
    }

    public String save(Entity entity) {
        if (null == entity.getId()) {
            entity.setId(UUID.randomUUID().toString());
        }
        Set<Entity> entities = new HashSet<>(findAll());
        entities.add(entity);
        jsonWriter.write(dataSourceFilePath, List.of(entities));
        return entity.getId();
    }

    public void saveAll(List<Entity> entities) {
        Set<Entity> originalEntities = new HashSet<>(jsonReader.read(dataSourceFilePath));
        originalEntities.addAll(entities);
        jsonWriter.write(dataSourceFilePath, List.of(originalEntities));
    }

    public Entity findOne(String id) {
        List<Entity> entities = jsonReader.read(dataSourceFilePath);

        return entities.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Entity> findAll() {
        return jsonReader.read(dataSourceFilePath);
    }

    public List<Entity> findAllById(List<String> ids) {
        List<Entity> entities = jsonReader.read(dataSourceFilePath);
        return entities.stream().filter(entity -> ids.contains(entity.getId())).collect(Collectors.toList());
    }

    public List<String> findAllId() {
        List<Entity> entities = jsonReader.read(dataSourceFilePath);
        return entities.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

}
