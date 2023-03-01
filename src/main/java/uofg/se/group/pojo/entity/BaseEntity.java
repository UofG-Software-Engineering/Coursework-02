package uofg.se.group.pojo.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    protected String id;

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(BaseEntity baseEntity) {
        return id.equals(baseEntity.getId());
    }
}
