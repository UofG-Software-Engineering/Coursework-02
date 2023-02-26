package uofg.se.group.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import uofg.se.group.constant.RequirementStatusEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@SuperBuilder
public class Requirement extends BaseEntity{

    private String courseId;
    private String courseDirectorId;
    private RequirementStatusEnum status;

}
