package petcare.com.petcare.services.serviceImp;

import petcare.com.petcare.config.Conexion;
import petcare.com.petcare.dto.DuenoDto;
import petcare.com.petcare.entity.DuenoEntity;
import petcare.com.petcare.mapping.DuenoMapper;
import petcare.com.petcare.repository.DuenoRepository;
import petcare.com.petcare.services.DuenoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DuenoServiceImpl implements DuenoService {

    private final DuenoRepository duenoRepository;

    public DuenoServiceImpl() throws SQLException {
        Connection connection = Conexion.getConexion();
        this.duenoRepository = new DuenoRepository(connection);
    }


    @Override
    public void registrar(DuenoDto duenoDto) throws SQLException {
        if (duenoDto.getCedula() == null || duenoDto.getCedula().isBlank())
            throw new IllegalArgumentException("La cédula es obligatoria.");

        if (duenoDto.getNombre() == null || duenoDto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio.");

        if (duenoRepository.buscarPorCedula(duenoDto.getCedula()) != null)
            throw new IllegalArgumentException("Ya existe un dueño con esa cedula.");

        DuenoEntity duenoEntity = DuenoMapper.INSTANCE.toEntity(duenoDto);
        duenoRepository.guardar(duenoEntity);

    }

    @Override
    public List<DuenoDto> listarTodos() throws SQLException {
        return duenoRepository.listarTodos()
                .stream()
                .map(DuenoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public DuenoDto buscarPorCedula(String cedula) throws SQLException {
        DuenoEntity entity = duenoRepository.buscarPorCedula(cedula);
        return DuenoMapper.INSTANCE.toDto(entity);
    }


    @Override
    public void actualizar(DuenoDto duenoDto) throws SQLException {
        if (duenoDto.getCedula() == null || duenoDto.getCedula().isBlank())
            throw new IllegalArgumentException("La cedula es obligatoria para actualizar.");
        if (duenoRepository.buscarPorCedula(duenoDto.getCedula()) == null)
            throw new IllegalArgumentException("No existe un dueño con esa cedula");

        duenoRepository.actualizar(DuenoMapper.INSTANCE.toEntity(duenoDto));

    }

    @Override
    public void eliminar(String cedula) throws SQLException {
        duenoRepository.eliminar(cedula);

    }
}
