package uofg.se.group.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.pojo.entity.BaseEntity;
import uofg.se.group.repo.BaseRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseService<T extends BaseEntity, Repo extends BaseRepo<T>> {

    @Autowired
    private T t;
    @Autowired
    protected Repo repo;

    public String save(T entity) {
         return repo.save(entity);
    }

    public void saveAll(List<T> entities) {
        repo.saveAll(entities);
    }

    public T findOne(String id) {
        T entity = repo.findOne(id);
        if (entity == null) {
            throw new DataNotFoundException(id);
        }

        return entity;
    }

    public List<T> findAll() {
        return repo.findAll();
    }

    public List<T> findAllById(List<String> ids) {
        return repo.findAllById(ids);
    }

    public List<String> findAllId() {
        return repo.findAllId();
    }
}
