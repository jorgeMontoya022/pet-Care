package petcare.com.petcare.services.serviceImp;

import petcare.com.petcare.config.Conexion;
import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.entity.HistoriaClinicaEntity;
import petcare.com.petcare.mapping.HistoriaClinicaMapper;
import petcare.com.petcare.repository.HistoriaCliniaRepository;
import petcare.com.petcare.services.HistoriaClinicaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaCliniaRepository historiaCliniaRepository;

    public HistoriaClinicaServiceImpl() throws SQLException {
        Connection connection = Conexion.getConexion();
        this.historiaCliniaRepository = new HistoriaCliniaRepository(connection);
    }

    @Override
    public void registrar(HistoriaClinicaDto historiaClinicaDto, Long idMascota) throws SQLException {
        if (historiaClinicaDto.getDescripcion() == null || historiaClinicaDto.getDescripcion().isBlank())
            throw new IllegalArgumentException("La descripción del diagnóstico es obligatoria.");
        if (idMascota == null)
            throw new IllegalArgumentException("El ID de la mascota es obligatorio.");

        HistoriaClinicaEntity entity = HistoriaClinicaMapper.INSTANCE.toEntity(historiaClinicaDto, idMascota);
        historiaCliniaRepository.guardar(entity);

    }

    @Override
    public List<HistoriaClinicaDto> buscarPorMascota(Long idMascota) throws SQLException {
        return historiaCliniaRepository.buscarPorMascota(idMascota);
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        historiaCliniaRepository.eliminar(id);

    }
}
