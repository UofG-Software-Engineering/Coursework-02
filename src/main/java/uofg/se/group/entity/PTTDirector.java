package uofg.se.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class PTTDirector extends BasePerson {

    public PTTDirector(String id, String name, PersonTypeEnum personType) {
        super(id, name, personType);
    }
}
