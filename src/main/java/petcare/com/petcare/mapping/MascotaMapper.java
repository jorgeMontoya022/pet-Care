package petcare.com.petcare.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.entity.MascotaEntity;

@Mapper
public interface MascotaMapper {

    MascotaMapper INSTANCE = Mappers.getMapper(MascotaMapper.class);

    // Entity → DTO: nombreDueno y cedulaDueno vienen de parámetros extra
    @Mapping(target = "nombreDueno", source = "nombreDueno")
    @Mapping(target = "cedulaDueno", source = "cedulaDueno")
    MascotaDto toDto(MascotaEntity entity, String nombreDueno, String cedulaDueno);

    // DTO → Entity: idDueno viene del Service (no del DTO)
    @Mapping(target = "idDueno", source = "idDueno")
    MascotaEntity toEntity(MascotaDto dto, Long idDueno);
}