package petcare.com.petcare.repository;

import petcare.com.petcare.dto.CitaDto;
import petcare.com.petcare.entity.CitaEntity;
import petcare.com.petcare.mapping.CitaMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaRepository {

    private final Connection con;

    public CitaRepository(Connection con) {
        this.con = con;
    }

    public void guardar(CitaEntity cita) throws SQLException {
        String sql = "INSERT INTO CITAS (idMascota, idVeterinario, fechaHora, motivo, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, cita.getIdMascota());
            ps.setLong(2, cita.getIdVeterinario());
            ps.setTimestamp(3, Timestamp.valueOf(cita.getFechaHora()));
            ps.setString(4, cita.getMotivo());
            ps.setString(5, cita.getEstado() != null ? cita.getEstado() : "PROGRAMADA");
            ps.executeUpdate();
        }
    }

    public List<CitaDto> listarTodas() throws SQLException {
        List<CitaDto> lista = new ArrayList<>();
        String sql = """
                SELECT c.idCita, c.fechaHora, c.motivo, c.estado,
                       m.nombre AS nombreMascota,
                       v.nombre AS nombreVeterinario,
                       d.cedula AS cedulaDueno
                FROM CITAS c
                JOIN MASCOTAS m     ON c.idMascota = m.idMascota
                JOIN VETERINARIOS v ON c.idVeterinario = v.idVeterinario
                JOIN DUENOS d       ON m.idDueno = d.idDueno
                ORDER BY c.fechaHora DESC
                """;
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCitaDto(rs));
            }
        }
        return lista;
    }

    public void cambiarEstado(Long idCita, String nuevoEstado) throws SQLException {
        String sql = "UPDATE CITAS SET estado = ? WHERE idCita = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setLong(2, idCita);
            ps.executeUpdate();
        }
    }

    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM CITAS WHERE idCita = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private CitaDto mapearCitaDto(ResultSet rs) throws SQLException {
        CitaEntity entity = new CitaEntity();
        entity.setIdCita(rs.getLong("idCita"));
        entity.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
        entity.setMotivo(rs.getString("motivo"));
        entity.setEstado(rs.getString("estado"));

        return CitaMapper.INSTANCE.toDto(
                entity,
                rs.getString("nombreMascota"),
                rs.getString("nombreVeterinario"),
                rs.getString("cedulaDueno")
        );
    }
}