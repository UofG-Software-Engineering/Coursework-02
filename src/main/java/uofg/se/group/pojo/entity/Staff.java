package uofg.se.group.pojo.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description Staff entity
 * @Author Xiaohui Yu
 * @Date 2023/3/1
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends Person{

    private List<Skill> skills;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Staff) {
            return id.equals(((Staff) obj).getId());
        }
        return false;
    }
}
