package uofg.se.group.pojo.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description Base entity
 * @Author Xiaohui Yu
 * @Date 2023/2/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    protected String id;
    protected String createdTime;
    protected String updatedTime;

}
