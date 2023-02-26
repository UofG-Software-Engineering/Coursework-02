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
public class PTTDirector extends BasePerson{

    public PTTDirector(String id, String name, PersonTypeEnum type) {
        super(id, name, type);
    }

}
