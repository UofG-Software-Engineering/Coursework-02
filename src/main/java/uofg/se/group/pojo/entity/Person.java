package uofg.se.group.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RoleEnum;

/**
 * @Description Person entity
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Person extends BaseEntity {

    protected String name;
    protected String role;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return id.equals(((Person) obj).getId());
        }
        return false;
    }
}
