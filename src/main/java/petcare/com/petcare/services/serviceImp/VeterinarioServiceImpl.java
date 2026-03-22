package petcare.com.petcare.services.serviceImp;

import petcare.com.petcare.config.Conexion;
import petcare.com.petcare.dto.VeterinarioDto;
import petcare.com.petcare.entity.VeterinarioEntity;
import petcare.com.petcare.mapping.VeterinarioMapper;
import petcare.com.petcare.repository.VeterinarioRepository;
import petcare.com.petcare.services.VeterinarioService;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import java.util.stream.Collectors;

public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl() throws SQLException {
        Connection con = Conexion.getConexion();
        this.veterinarioRepository = new VeterinarioRepository(con);
    }

    @Override
    public void registrar(VeterinarioDto veterinarioDto) throws SQLException {
        if (veterinarioDto.getNombre() == null || veterinarioDto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre del veterinario es obligatorio.");
        if (veterinarioDto.getTarjetaProfesional() == null || veterinarioDto.getTarjetaProfesional().isBlank())
            throw new IllegalArgumentException("La tarjeta profesional es obligatoria.");

        veterinarioRepository.guardar(VeterinarioMapper.INSTANCE.toEntity(veterinarioDto));

    }

    @Override
    public List<VeterinarioDto> listarTodos() throws SQLException {
        return veterinarioRepository.listarTodos()
                .stream()
                .map(VeterinarioMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarioDto buscarPorId(Long id) throws SQLException {
        VeterinarioEntity entity = veterinarioRepository.buscarPorId(id);
        return VeterinarioMapper.INSTANCE.toDto(entity);
    }

    @Override
    public void actualizar(VeterinarioDto dto) throws SQLException {
        if (dto.getIdVeterinario() == null)
            throw new IllegalArgumentException("El ID es obligatorio para actualizar.");
        if (veterinarioRepository.buscarPorId(dto.getIdVeterinario()) == null)
            throw new IllegalArgumentException("No existe un veterinario con ese ID.");

        veterinarioRepository.actualizar(VeterinarioMapper.INSTANCE.toEntity(dto));

    }

    @Override
    public VeterinarioDto login(String tarjetaProfesional, String contrasena) throws SQLException {
        if (tarjetaProfesional == null || tarjetaProfesional.isBlank())
            throw new IllegalArgumentException("La tarjeta profesional es obligatoria.");
        if (contrasena == null || contrasena.isBlank())
            throw new IllegalArgumentException("La contraseña es obligatoria.");

        VeterinarioEntity entity = veterinarioRepository.login(tarjetaProfesional, contrasena);
        if (entity == null)
            throw new IllegalArgumentException("Tarjeta profesional o contraseña incorrecta.");

        return VeterinarioMapper.INSTANCE.toDto(entity);
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        veterinarioRepository.eliminar(id);

    }
}
