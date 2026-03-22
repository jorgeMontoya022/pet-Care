package petcare.com.petcare.services;

import petcare.com.petcare.dto.DuenoDto;

import java.sql.SQLException;
import java.util.List;

public interface DuenoService {

    void registrar(DuenoDto duenoDto) throws SQLException;

    List<DuenoDto> listarTodos() throws SQLException;

    DuenoDto buscarPorCedula(String cedula) throws SQLException;

    void actualizar(DuenoDto dto) throws SQLException;

    void eliminar (String cedula) throws SQLException;
}
