package uofg.se.group.service;

import java.util.List;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.Training;
import uofg.se.group.pojo.vo.TrainingVO;
import uofg.se.group.dao.TrainingRepo;

/**
 * @Description Training service
 * @Author Xiaohui Yu
 * @Date 2023/3/7
 */
@Component
public class TrainingService extends BaseService<Training, TrainingRepo>{

    public void addStaff(String trainingId, List<String> staffIds) {
        repo.addStaff(trainingId, staffIds);
    }

    public TrainingVO findOneVO(String trainingId) {
        return repo.findOneVO(trainingId);
    }
}
