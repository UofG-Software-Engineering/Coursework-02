package uofg.se.group.service;

import uofg.se.group.entity.Staff;
import uofg.se.group.inject.Injector;
import uofg.se.group.repo.StaffRepo;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/27
 */
public class StaffService extends BaseService<Staff, StaffRepo>{
    public StaffService() {
        super(Injector.getInstance(StaffRepo.class));
    }
}
