package uofg.se.group.service;

import java.util.List;
import uofg.se.group.entity.BaseEntity;
import uofg.se.group.exception.DataNotFoundException;
import uofg.se.group.inject.Autowired;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.BaseRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public abstract class BaseService<T extends BaseEntity, Repo extends BaseRepo<T>> {

    protected Repo repo;

    public BaseService(Repo repo) {
        this.repo = repo;
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
}
