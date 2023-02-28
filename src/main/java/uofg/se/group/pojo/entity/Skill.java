package uofg.se.group.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class Skill extends BaseEntity {

    private String name;
}
