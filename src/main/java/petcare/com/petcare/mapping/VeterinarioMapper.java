package petcare.com.petcare.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import petcare.com.petcare.dto.VeterinarioDto;
import petcare.com.petcare.entity.VeterinarioEntity;

@Mapper
public interface VeterinarioMapper {

    VeterinarioMapper INSTANCE = Mappers.getMapper(VeterinarioMapper.class);

    VeterinarioDto toDto(VeterinarioEntity entity);
    VeterinarioEntity toEntity(VeterinarioDto dto);
}