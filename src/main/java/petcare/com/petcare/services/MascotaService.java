package petcare.com.petcare.services;

import petcare.com.petcare.dto.MascotaDto;

import java.sql.SQLException;
import java.util.List;

public interface MascotaService {

    void registrar(MascotaDto dto) throws SQLException;

    List<MascotaDto> listarTodas() throws SQLException;

    MascotaDto buscarPorId(Long id) throws SQLException;

    List<MascotaDto> buscarPorCedulaDueno(String cedula) throws SQLException;

    void actualizar(MascotaDto dto) throws SQLException;

    void eliminar(Long id) throws SQLException;


}
