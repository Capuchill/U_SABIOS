package com.proyectito.repository.impl.implsalon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectito.repository.RepositorySalon;
import com.proyectito.repository.models.Salon;
import com.proyectito.utils.TextStyles;
import com.proyectito.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositorySalonMysqlImpl implements RepositorySalon{

     private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Salon> listar() {
        List<Salon> listSalones = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM class_rooms")) {
            while (rs.next()) {
                listSalones.add(crearSalon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSalones;
    }

    @Override
    public Salon porId(int ID) {
        Salon salon = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM class_rooms WHERE id_class_room=?")) {
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    salon = crearSalon(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salon;
    }


    @Override
    public void crear(Salon salon) {
        String sql = "INSERT INTO class_rooms(reference_class_room,quota,id_floor) VALUES(?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salon.getReference());
            stmt.setInt(2, salon.getCapacidad());
            stmt.setInt(3, salon.getId_piso());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            TextStyles.msgError(throwables.getMessage());
        }
    }

    @Override
    public void editar(Salon salon, int id) {
        String sql = "UPDATE class_rooms SET reference_class_room = ?,quota = ?,id_floor = ? WHERE id_class_room=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salon.getReference());
            stmt.setInt(2, salon.getCapacidad());
            stmt.setInt(3, salon.getId_piso());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Salon salon, int id) {
        String sql = "DELETE FROM class_rooms WHERE id_class_room=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Salon crearSalon(ResultSet rs) throws SQLException {
        Salon salon = new Salon();
        salon.setReference(rs.getString("reference_class_room"));
        salon.setCapacidad(rs.getInt("quota"));
        salon.setId_piso(rs.getInt("id_floor"));
        return salon;
    }

    @Override
    public Salon porReferencia(String ref) {
        Salon salon = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM class_rooms WHERE reference_class_room=?")) {
            stmt.setString(1, ref);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    salon = crearSalon(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salon;
    }
    
}
