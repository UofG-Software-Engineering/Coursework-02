package uofg.se.group.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@SuperBuilder
@Component
public class Requirement extends BaseEntity{

    private String courseId;
    private String courseDirectorId;
    private List<Skill> skills;
    private RequirementStatusEnum status;

}
