package petcare.com.petcare.services.serviceImp;

import petcare.com.petcare.config.Conexion;
import petcare.com.petcare.dto.CitaDto;
import petcare.com.petcare.entity.CitaEntity;
import petcare.com.petcare.mapping.CitaMapper;
import petcare.com.petcare.repository.CitaRepository;
import petcare.com.petcare.repository.MascotaRepository;
import petcare.com.petcare.repository.VeterinarioRepository;
import petcare.com.petcare.services.CitaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public static final List<String> ESTADOS_VALIDOS = Arrays.asList("PROGRAMADA", "COMPLETADA", "CANCELADA");

    public CitaServiceImpl() throws SQLException {
        Connection connection = Conexion.getConexion();
        this.citaRepository = new CitaRepository(connection);
        this.mascotaRepository = new MascotaRepository(connection);
        this.veterinarioRepository = new VeterinarioRepository(connection);
    }

    @Override
    public void registrar(CitaDto citaDto) throws SQLException {

        if (citaDto.getFechaHora() == null)
            throw new IllegalArgumentException("La fecha y hora son obligatorias.");
        if (citaDto.getMotivo() == null || citaDto.getMotivo().isBlank())
            throw new IllegalArgumentException("El motivo es obligatorio.");

        // Verificar que la mascota existe
        var mascotaDto = mascotaRepository.buscarPorId(citaDto.getIdCita());
        if (mascotaDto == null)
            throw new IllegalArgumentException("No existe una mascota con ese ID.");

        // Verificar que el veterinario existe
        var vet = veterinarioRepository.buscarPorId(citaDto.getIdCita());
        if (vet == null)
            throw new IllegalArgumentException("No existe un veterinario con ese ID.");

        CitaEntity entity = CitaMapper.INSTANCE.toEntity(
                citaDto,
                citaDto.getIdCita(),       // idMascota
                vet.getIdVeterinario() // idVeterinario
        );
        citaRepository.guardar(entity);


    }

    @Override
    public List<CitaDto> listarTodas() throws SQLException {
        return citaRepository.listarTodas();
    }

    @Override
    public void cambiarEstado(Long idCita, String estado) throws SQLException {
        if (!ESTADOS_VALIDOS.contains(estado.toUpperCase()))
            throw new IllegalArgumentException("Estado inválido. Use: PROGRAMADA, COMPLETADA o CANCELADA");

        citaRepository.cambiarEstado(idCita, estado.toUpperCase());

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        citaRepository.eliminar(id);

    }
}
