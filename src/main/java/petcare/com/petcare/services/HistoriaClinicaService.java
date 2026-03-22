package petcare.com.petcare.services;

import petcare.com.petcare.dto.HistoriaClinicaDto;

import java.sql.SQLException;
import java.util.List;

public interface HistoriaClinicaService {

    void registrar(HistoriaClinicaDto historiaClinicaDto, Long idMascota) throws SQLException;

    List<HistoriaClinicaDto> buscarPorMascota(Long idMascota) throws SQLException;

    void eliminar(Long id) throws SQLException;

}
