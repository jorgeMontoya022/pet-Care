package petcare.com.petcare.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import petcare.com.petcare.dto.DuenoDto;
import petcare.com.petcare.entity.DuenoEntity;

@Mapper
public interface DuenoMapper {

    DuenoMapper INSTANCE = Mappers.getMapper(DuenoMapper.class);


    DuenoDto toDto(DuenoEntity entity);

    @Mapping(target = "idDueno", ignore = true)
    DuenoEntity toEntity(DuenoDto dto);
}