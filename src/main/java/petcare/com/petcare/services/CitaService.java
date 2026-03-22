package petcare.com.petcare.services;

import petcare.com.petcare.dto.CitaDto;

import java.sql.SQLException;
import java.util.List;

public interface CitaService {
    void registrar(CitaDto dto) throws SQLException;

    List<CitaDto> listarTodas() throws SQLException;

    void cambiarEstado(Long idCita, String estado) throws SQLException;

    void eliminar(Long id) throws SQLException;
}
