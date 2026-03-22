package petcare.com.petcare.repository;


import petcare.com.petcare.entity.DuenoEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenoRepository {

    private final Connection con;

    public DuenoRepository(Connection con) {
        this.con = con;
    }

    // ── CREATE ──────────────────────────────────────────────────
    public void guardar(DuenoEntity dueno) throws SQLException {
        String sql = "INSERT INTO DUENOS (cedula, nombre, telefono, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dueno.getCedula());
            ps.setString(2, dueno.getNombre());
            ps.setString(3, dueno.getTelefono());
            ps.setString(4, dueno.getCorreo());
            ps.executeUpdate();
        }
    }

    // ── READ ALL ─────────────────────────────────────────────────
    public List<DuenoEntity> listarTodos() throws SQLException {
        List<DuenoEntity> lista = new ArrayList<>();
        String sql = "SELECT idDueno, cedula, nombre, telefono, correo FROM DUENOS";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearDueno(rs));
            }
        }
        return lista;
    }

    // ── READ BY CEDULA ───────────────────────────────────────────
    public DuenoEntity buscarPorCedula(String cedula) throws SQLException {
        String sql = "SELECT idDueno, cedula, nombre, telefono, correo FROM DUENOS WHERE cedula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearDueno(rs);
            }
        }
        return null;
    }

    // ── READ BY ID ───────────────────────────────────────────────
    public DuenoEntity buscarPorId(Long id) throws SQLException {
        String sql = "SELECT idDueno, cedula, nombre, telefono, correo FROM DUENOS WHERE idDueno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearDueno(rs);
            }
        }
        return null;
    }

    // ── UPDATE ───────────────────────────────────────────────────
    public void actualizar(DuenoEntity dueno) throws SQLException {
        String sql = "UPDATE DUENOS SET nombre=?, telefono=?, correo=? WHERE cedula=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dueno.getNombre());
            ps.setString(2, dueno.getTelefono());
            ps.setString(3, dueno.getCorreo());
            ps.setString(4, dueno.getCedula());
            ps.executeUpdate();
        }
    }

    // ── DELETE ───────────────────────────────────────────────────
    public void eliminar(String cedula) throws SQLException {
        String sql = "DELETE FROM DUENOS WHERE cedula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
        }
    }

    // ── MAPPER INTERNO ───────────────────────────────────────────
    private DuenoEntity mapearDueno(ResultSet rs) throws SQLException {
        DuenoEntity d = new DuenoEntity();
        d.setIdDueno(rs.getLong("idDueno"));
        d.setCedula(rs.getString("cedula"));
        d.setNombre(rs.getString("nombre"));
        d.setTelefono(rs.getString("telefono"));
        d.setCorreo(rs.getString("correo"));
        return d;
    }


}
