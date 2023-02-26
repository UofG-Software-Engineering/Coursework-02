package uofg.se.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import uofg.se.group.constant.PersonTypeEnum;

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
public abstract class BasePerson extends BaseEntity{

    protected String name;
    protected PersonTypeEnum personType;

    public BasePerson(String id, String name, PersonTypeEnum personType) {
        super(id);
        this.name = name;
        this.personType = personType;
    }
}
