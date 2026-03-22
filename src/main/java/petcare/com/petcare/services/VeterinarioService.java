package petcare.com.petcare.services;

import petcare.com.petcare.dto.VeterinarioDto;

import java.sql.SQLException;
import java.util.List;

public interface VeterinarioService {

    void registrar(VeterinarioDto veterinarioDto) throws SQLException;

    List<VeterinarioDto> listarTodos() throws SQLException;

    VeterinarioDto buscarPorId(Long id) throws SQLException;

    void actualizar(VeterinarioDto dto) throws SQLException;

    void eliminar(Long id) throws SQLException;

    VeterinarioDto login(String tarjetaProfesional, String contrasena) throws SQLException;


}
