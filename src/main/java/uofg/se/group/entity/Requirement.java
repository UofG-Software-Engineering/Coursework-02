package uofg.se.group.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uofg.se.group.constant.RequirementStatusEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Requirement extends BaseEntity{

    private RequirementStatusEnum status;

    public Requirement(String id, RequirementStatusEnum status) {
        super(id);
        this.status = status;
    }
}
