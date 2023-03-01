package uofg.se.group.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.BaseEntity;
import uofg.se.group.util.JsonReader;
import uofg.se.group.util.JsonWriter;

/**
 * @Description
 * @Author Chris
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
        if (null == entity.getId()) {
            entity.setId(UUID.randomUUID().toString());
        }
        Set<Entity> entities = new HashSet<>(findAll());
        entities.add(entity);
        jsonWriter.write(dataSourceFilePath, entities);
        return entity.getId();
    }

    public void saveAll(List<Entity> entities) {
        Set<Entity> originalEntities = new HashSet<>(findAll());
        entities.forEach(entity -> {
            if (null == entity.getId()) {
                entity.setId(UUID.randomUUID().toString());
            }
            originalEntities.add(entity);
        });
        jsonWriter.write(dataSourceFilePath, originalEntities);
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

}
