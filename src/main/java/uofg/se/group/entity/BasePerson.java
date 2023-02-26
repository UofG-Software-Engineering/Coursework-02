package uofg.se.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uofg.se.group.constant.PersonTypeEnum;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePerson extends BaseEntity{

    protected String name;
    protected PersonTypeEnum personType;

    public BasePerson(String id, String name, PersonTypeEnum type) {
        super(id);
        this.name = name;
        this.personType = type;
    }
}
