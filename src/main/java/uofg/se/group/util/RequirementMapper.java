package uofg.se.group.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uofg.se.group.pojo.entity.Requirement;
import uofg.se.group.pojo.vo.RequirementVO;

/**
 * @Description
 * @Author Chris
 * @Date 2023/3/12
 */
@Mapper
public interface RequirementMapper {

    RequirementMapper INSTANCE = Mappers.getMapper(RequirementMapper.class);

    RequirementVO Entity2VO (Requirement requirement);
}
