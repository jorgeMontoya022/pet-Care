package petcare.com.petcare.repository;

import petcare.com.petcare.entity.VeterinarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepository {
    private final Connection con;

    public VeterinarioRepository(Connection con) {
        this.con = con;
    }

    public void guardar(VeterinarioEntity vet) throws SQLException {
        String sql = "INSERT INTO VETERINARIOS (nombre, especialidad, tarjetaProfesional) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getEspecialidad());
            ps.setString(3, vet.getTarjetaProfesional());
            ps.executeUpdate();
        }
    }

    public List<VeterinarioEntity> listarTodos() throws SQLException {
        List<VeterinarioEntity> lista = new ArrayList<>();
        String sql = "SELECT idVeterinario, nombre, especialidad, tarjetaProfesional FROM VETERINARIOS";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearVet(rs));
            }
        }
        return lista;
    }

    public VeterinarioEntity buscarPorId(Long id) throws SQLException {
        String sql = "SELECT idVeterinario, nombre, especialidad, tarjetaProfesional FROM VETERINARIOS WHERE idVeterinario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearVet(rs);
            }
        }
        return null;
    }

    public void actualizar(VeterinarioEntity vet) throws SQLException {
        String sql = "UPDATE VETERINARIOS SET nombre=?, especialidad=?, tarjetaProfesional=? WHERE idVeterinario=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getEspecialidad());
            ps.setString(3, vet.getTarjetaProfesional());
            ps.setLong(4, vet.getIdVeterinario());
            ps.executeUpdate();
        }
    }

    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM VETERINARIOS WHERE idVeterinario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public VeterinarioEntity login(String tarjetaProfesional, String contrasena) throws SQLException {
        String sql = "SELECT idVeterinario, nombre, especialidad, tarjetaProfesional " +
                     "FROM VETERINARIOS WHERE tarjetaProfesional = ? AND contrasena = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tarjetaProfesional);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearVet(rs);
            }
        }
        return null; // null = credenciales incorrectas
    }

    private VeterinarioEntity mapearVet(ResultSet rs) throws SQLException {
        VeterinarioEntity v = new VeterinarioEntity();
        v.setIdVeterinario(rs.getLong("idVeterinario"));
        v.setNombre(rs.getString("nombre"));
        v.setEspecialidad(rs.getString("especialidad"));
        v.setTarjetaProfesional(rs.getString("tarjetaProfesional"));
        return v;
    }

}
