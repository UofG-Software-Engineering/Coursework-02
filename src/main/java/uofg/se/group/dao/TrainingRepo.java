package uofg.se.group.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.EntityEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Training;
import uofg.se.group.pojo.vo.TrainingVO;

/**
 * @Description Training repository
 * @Author Xiaohui Yu
 * @Date 2023/3/7
 */
@Component
public class TrainingRepo extends BaseRepo<Training>{

    @Resource
    private PersonRepo personRepo;

    public TrainingRepo() {
        super(EntityEnum.TRAINING.getDataSourceFilePath());
    }

    public void addStaff(String trainingId, List<String> staffIds) {
        Training training = findOne(trainingId);
        training.setStaffIds(staffIds);
        save(training);
    }

    public TrainingVO findOneVO(String trainingId) {
        Training training = findOne(trainingId);
        List<String> staffIds = training.getStaffIds();
        List<Person> staffs = personRepo.findAllById(staffIds);
        return new TrainingVO(training.getName(), staffs);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> findAll() {
        return (List<Training>) jsonReader.read(dataSourceFilePath, Training.class);
    }
}
