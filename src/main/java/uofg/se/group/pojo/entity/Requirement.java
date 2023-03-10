package uofg.se.group.pojo.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;
import uofg.se.group.constant.RequirementStatusEnum;

/**
 * @Description Teaching requirement entity
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Component
public class Requirement extends BaseEntity {

    private String courseId;
    private String courseDirectorId;
    private String staffId;
    private List<String> skillIds;
    private RequirementStatusEnum status;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Requirement) {
            return id.equals(((Requirement) obj).getId());
        }
        return false;
    }
}
