package uofg.se.group.pojo.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import uofg.se.group.pojo.entity.Person;

/**
 * @Description
 * @Author Chris
 * @Date 2023/3/7
 */
@AllArgsConstructor
@ToString
public class TrainingVO {

    private String name;
    private List<Person> staffs;

}
