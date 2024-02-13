package com.proyectito.repository.impl.impldepartamento;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.proyectito.repository.RepositoryDepartamento;
import com.proyectito.repository.models.Departamento;
import com.proyectito.utils.TextStyles;
import com.proyectito.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryDepartamentoMysqlImpl implements RepositoryDepartamento {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Departamento> listar() {

        List<Departamento> listDepartamentos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM departments")) {
            while (rs.next()) {
                listDepartamentos.add(crearDepartamento(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listDepartamentos;

    }

    @Override
    public Departamento porId(int ID) {
        Departamento depart = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departments WHERE id_department=?")) {
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    depart = crearDepartamento(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depart;

    }

    @Override
    public void crear(Departamento departamento) {

        String sql = "INSERT INTO departments(name_department) VALUES(?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamento.getNombre());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            TextStyles.msgError(throwables.getMessage());
        }
    }

    @Override
    public void editar(Departamento departamento, int id) {

        String sql = "UPDATE departments SET name_department=? WHERE id_department=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamento.getNombre());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Departamento departamento, int id) {
        String sql = "DELETE FROM departments WHERE id_department=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Departamento crearDepartamento(ResultSet rs) throws SQLException {
        Departamento depart = new Departamento();
        depart.setNombre(rs.getString("name_department"));
        return depart;
    }

}
