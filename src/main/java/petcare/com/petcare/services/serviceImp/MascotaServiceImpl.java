package petcare.com.petcare.services.serviceImp;

import petcare.com.petcare.config.Conexion;
import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.entity.DuenoEntity;
import petcare.com.petcare.entity.MascotaEntity;
import petcare.com.petcare.mapping.MascotaMapper;
import petcare.com.petcare.repository.DuenoRepository;
import petcare.com.petcare.repository.MascotaRepository;
import petcare.com.petcare.services.MascotaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DuenoRepository duenoRepository;

    public MascotaServiceImpl() throws SQLException {
        Connection con = Conexion.getConexion();
        this.mascotaRepository = new MascotaRepository(con);
        this.duenoRepository = new DuenoRepository(con);
    }

    @Override
    public void registrar(MascotaDto mascotaDto) throws SQLException {

        if (mascotaDto.getNombre() == null || mascotaDto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre de la mascota es obligatorio.");
        if (mascotaDto.getCedulaDueno() == null || mascotaDto.getCedulaDueno().isBlank())
            throw new IllegalArgumentException("La cédula del dueño es obligatoria.");

        DuenoEntity dueno = duenoRepository.buscarPorCedula(mascotaDto.getCedulaDueno());
        if (dueno == null)
            throw new IllegalArgumentException("No existe un dueño con la cédula: " + mascotaDto.getCedulaDueno());

        MascotaEntity entity = MascotaMapper.INSTANCE.toEntity(mascotaDto, dueno.getIdDueno());
        mascotaRepository.guardar(entity);

    }

    @Override
    public List<MascotaDto> listarTodas() throws SQLException {
        return mascotaRepository.listarTodas();
    }

    @Override
    public MascotaDto buscarPorId(Long id) throws SQLException {
        return mascotaRepository.buscarPorId(id);
    }

    @Override
    public List<MascotaDto> buscarPorCedulaDueno(String cedula) throws SQLException {
        return mascotaRepository.buscarPorCedulaDueno(cedula);
    }

    @Override
    public void actualizar(MascotaDto dto) throws SQLException {
        if (dto.getIdMascota() == null)
            throw new IllegalArgumentException("El ID de la mascota es obligatorio para actualizar.");

        DuenoEntity dueno = duenoRepository.buscarPorCedula(dto.getCedulaDueno());
        if (dueno == null)
            throw new IllegalArgumentException("No existe un dueño con esa cédula.");

        MascotaEntity entity = MascotaMapper.INSTANCE.toEntity(dto, dueno.getIdDueno());
        mascotaRepository.actualizar(entity);

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        mascotaRepository.eliminar(id);

    }
}
