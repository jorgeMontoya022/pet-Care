package petcare.com.petcare.repository;

import petcare.com.petcare.dto.MascotaDto;
import petcare.com.petcare.entity.MascotaEntity;
import petcare.com.petcare.mapping.MascotaMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaRepository {

    private final Connection con;

    public MascotaRepository(Connection con) {
        this.con = con;
    }

    public void guardar(MascotaEntity mascota) throws SQLException {
        String sql = "INSERT INTO MASCOTAS (nombre, especie, raza, edad, idDueno) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setInt(4, mascota.getEdad());
            ps.setLong(5, mascota.getIdDueno());
            ps.executeUpdate();
        }
    }

    public List<MascotaDto> listarTodas() throws SQLException {
        List<MascotaDto> lista = new ArrayList<>();
        String sql = """
                SELECT m.idMascota, m.nombre, m.especie, m.raza, m.edad,
                       d.nombre AS nombreDueno, d.cedula AS cedulaDueno,
                       d.telefono AS telefonoDueno, d.correo AS correoDueno
                FROM MASCOTAS m
                JOIN DUENOS d ON m.idDueno = d.idDueno
                """;
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearDto(rs));
            }
        }
        return lista;
    }

    public MascotaDto buscarPorId(Long id) throws SQLException {
        String sql = """
                SELECT m.idMascota, m.nombre, m.especie, m.raza, m.edad,
                       d.nombre AS nombreDueno, d.cedula AS cedulaDueno,
                       d.telefono AS telefonoDueno, d.correo AS correoDueno
                FROM MASCOTAS m
                JOIN DUENOS d ON m.idDueno = d.idDueno
                WHERE m.idMascota = ?
                """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearDto(rs);
            }
        }
        return null;
    }

    public List<MascotaDto> buscarPorCedulaDueno(String cedula) throws SQLException {
        List<MascotaDto> lista = new ArrayList<>();
        String sql = """
                SELECT m.idMascota, m.nombre, m.especie, m.raza, m.edad,
                       d.nombre AS nombreDueno, d.cedula AS cedulaDueno,
                       d.telefono AS telefonoDueno, d.correo AS correoDueno
                FROM MASCOTAS m
                JOIN DUENOS d ON m.idDueno = d.idDueno
                WHERE d.cedula = ?
                """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearDto(rs));
                }
            }
        }
        return lista;
    }

    public void actualizar(MascotaEntity mascota) throws SQLException {
        String sql = "UPDATE MASCOTAS SET nombre=?, especie=?, raza=?, edad=? WHERE idMascota=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setInt(4, mascota.getEdad());
            ps.setLong(5, mascota.getIdMascota());
            ps.executeUpdate();
        }
    }

    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM MASCOTAS WHERE idMascota = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // ── MAPPER INTERNO ───────────────────────────────────────────
    private MascotaDto mapearDto(ResultSet rs) throws SQLException {
        MascotaEntity entity = new MascotaEntity();
        entity.setIdMascota(rs.getLong("idMascota"));
        entity.setNombre(rs.getString("nombre"));
        entity.setEspecie(rs.getString("especie"));
        entity.setRaza(rs.getString("raza"));
        entity.setEdad(rs.getInt("edad"));

        MascotaDto dto = MascotaMapper.INSTANCE.toDto(
                entity,
                rs.getString("nombreDueno"),
                rs.getString("cedulaDueno")
        );

        // Campos extra para el PDF
        dto.setTelefonoDueno(rs.getString("telefonoDueno"));
        dto.setCorreoDueno(rs.getString("correoDueno"));
        return dto;
    }
}