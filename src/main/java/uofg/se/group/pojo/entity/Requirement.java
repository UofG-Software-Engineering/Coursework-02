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
public class Requirement extends BaseEntity {

    private String courseId;
    private String courseDirectorId;
    private String staffId;
    private List<String> skillIds;
    private RequirementStatusEnum status;

}
