package uofg.se.group.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StaffSkill extends BaseEntity {

    private String staffId;
    private String skillId;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StaffSkill) {
            StaffSkill staffSkill = (StaffSkill) obj;
            return staffSkill.getId().equals(this.getId());
        }
        return false;
    }
}
