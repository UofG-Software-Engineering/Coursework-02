package uofg.se.group.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class Course extends BaseEntity{

    private String name;
    private String courseDirectorId;
}
