package uofg.se.group.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class StaffSkill extends BaseEntity {

    private String staffId;
    private String skillId;
}