package uofg.se.group.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Component
public class Course extends BaseEntity {

    private String term;
    private String name;
    private String courseDirectorId;
}
