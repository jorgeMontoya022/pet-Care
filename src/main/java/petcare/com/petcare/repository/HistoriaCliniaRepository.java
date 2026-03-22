package petcare.com.petcare.repository;

import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.entity.HistoriaClinicaEntity;
import petcare.com.petcare.mapping.HistoriaClinicaMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaCliniaRepository {

    private final Connection con;

    public HistoriaCliniaRepository(Connection con) {
        this.con = con;
    }

    public void guardar(HistoriaClinicaEntity historiaClinica) throws SQLException {
        String sql = "INSERT INTO HISTORIASCLINICAS (idMascota, descripcion, medicacion, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setLong(1, historiaClinica.getIdMascota());
            ps.setString(2, historiaClinica.getDescripcion());
            ps.setString(3, historiaClinica.getMedicacion());
            ps.setDate(4, historiaClinica.getFecha() != null ? historiaClinica.getFecha() : new Date(System.currentTimeMillis()));
            ps.executeUpdate();
        }
    }

    // Historial completo de una mascota (ordenado del más reciente al más antiguo)
    public List<HistoriaClinicaDto> buscarPorMascota(Long idMascota) throws SQLException {
        List<HistoriaClinicaDto> lista = new ArrayList<>();
        String sql = """
                SELECT h.idHistorial, h.descripcion, h.medicacion, h.fecha,
                       m.nombre AS nombreMascota, m.especie
                FROM HISTORIASCLINICAS h
                JOIN MASCOTAS m ON h.idMascota = m.idMascota
                WHERE h.idMascota = ?
                ORDER BY h.fecha DESC
                """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HistoriaClinicaEntity entity = new HistoriaClinicaEntity();
                    entity.setIdHistorial(rs.getLong("idHistorial"));
                    entity.setIdMascota(idMascota);
                    entity.setDescripcion(rs.getString("descripcion"));
                    entity.setMedicacion(rs.getString("medicacion"));
                    entity.setFecha(rs.getDate("fecha"));

                    lista.add(HistoriaClinicaMapper.INSTANCE.toDto(
                            entity,
                            rs.getString("nombreMascota"),
                            rs.getString("especie")
                    ));
                }
            }
        }
        return lista;
    }

    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM HISTORIASCLINICAS WHERE idHistorial = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

}
