package uofg.se.group.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uofg.se.group.pojo.entity.Person;
import uofg.se.group.pojo.entity.Staff;

/**
 * @Description
 * @Author Chris
 * @Date 2023/3/1
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Staff Person2Staff(Person person);
}
