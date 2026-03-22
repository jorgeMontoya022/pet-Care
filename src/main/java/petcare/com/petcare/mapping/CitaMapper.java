package petcare.com.petcare.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import petcare.com.petcare.dto.CitaDto;
import petcare.com.petcare.entity.CitaEntity;

@Mapper
public interface CitaMapper {
    CitaMapper INSTANCE = Mappers.getMapper(CitaMapper.class);

    //Entity -> DTO: Los nombre vienen del JOIN en el Repository
    @Mapping(target = "nombreMascota", source = "nombreMascota")
    @Mapping(target = "nombreVeterinario", source = "nombreVeterinario")
    @Mapping(target = "cedulaDueno", source = "cedulaDueno")
    CitaDto toDto(CitaEntity entity, String nombreMascota, String nombreVeterinario, String cedulaDueno);

    // DTO → Entity: IDs los resuelve el Service
    @Mapping(target = "idMascota",     source = "idMascota")
    @Mapping(target = "idVeterinario", source = "idVeterinario")
    @Mapping(target = "idCita",        ignore = true) // Lo genera Oracle con SEQUENCE
    CitaEntity toEntity(CitaDto dto, Long idMascota, Long idVeterinario);
}
