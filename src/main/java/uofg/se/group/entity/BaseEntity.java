package uofg.se.group.entity;

import java.time.LocalDateTime;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    protected String id;

    // private LocalDateTime createTime;
    // private LocalDateTime updateTime;


    public int hashCode() {
        return id.hashCode();
    }
    public boolean equals(BaseEntity baseEntity) {
        return id.equals(baseEntity.getId());
    }
}
