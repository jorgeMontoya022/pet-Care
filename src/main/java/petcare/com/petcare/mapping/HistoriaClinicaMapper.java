package petcare.com.petcare.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.entity.HistoriaClinicaEntity;

@Mapper
public interface HistoriaClinicaMapper {

    HistoriaClinicaMapper INSTANCE = Mappers.getMapper(HistoriaClinicaMapper.class);

    // Entity → DTO
    @Mapping(target = "idHistorial",   source = "entity.idHistorial")
    @Mapping(target = "nombreMascota", source = "nombreMascota")
    @Mapping(target = "especie",       source = "especie")
    @Mapping(target = "fecha",         source = "entity.fecha") // sql.Date → LocalDate automático
    HistoriaClinicaDto toDto(HistoriaClinicaEntity entity, String nombreMascota, String especie);

    // DTO → Entity
    @Mapping(target = "idMascota",   source = "idMascota")
    @Mapping(target = "idHistorial", ignore = true) // Oracle lo genera con IDENTITY
    @Mapping(target = "fecha",       source = "dto.fecha") // LocalDate → sql.Date automático
    HistoriaClinicaEntity toEntity(HistoriaClinicaDto dto, Long idMascota);
}