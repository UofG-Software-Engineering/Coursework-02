package uofg.se.group.pojo.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Description Training entity
 * @Author Xiaohui Yu
 * @Date 2023/3/7
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Component
public class Training extends BaseEntity{

    private String name;
    private List<String> staffIds;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Training) {
            return StringUtils.equals(id, ((Training) obj).getId());
        }
        return false;
    }
}
