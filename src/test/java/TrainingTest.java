import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uofg.se.group.Application;
import uofg.se.group.constant.RoleEnum;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Training;
import uofg.se.group.pojo.vo.TrainingVO;
import uofg.se.group.repo.TrainingRepo;
import uofg.se.group.service.PersonService;
import uofg.se.group.service.TrainingService;

/**
 * @Description
 * @Author Chris
 * @Date 2023/3/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@Slf4j
public class TrainingTest {

    @Resource
    private PersonService personService;
    @Resource
    private TrainingService trainingService;

    @Test
    public void addTraining() {
        List<Person> people = new ArrayList<>() {{
            add(new Person("Chris", RoleEnum.PTT_DIRECTOR.getValue()));
            add(new Person("Mars", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Christina", RoleEnum.COURSE_DIRECTOR.getValue()));
            add(new Person("Louise", RoleEnum.STAFF.getValue()));
            add(new Person("Gray", RoleEnum.STAFF.getValue()));
        }};
        personService.saveAll(people);
        List<Person> staffs = personService.findAllByRole(RoleEnum.STAFF);
        List<String> staffIds = staffs.stream().map(Person::getId).collect(Collectors.toList());
        String trainingId = trainingService.save(Training.builder().name("Training 1").build());
        trainingService.addStaff(trainingId, staffIds);
        TrainingVO trainingVO = trainingService.findOneVO(trainingId);
        log.info("Training: {}", trainingVO);
    }
}
