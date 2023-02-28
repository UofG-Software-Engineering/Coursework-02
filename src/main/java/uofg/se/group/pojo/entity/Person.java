package uofg.se.group.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RoleEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@Component
public class Person extends BaseEntity {

    protected String name;
    protected RoleEnum role;

}
