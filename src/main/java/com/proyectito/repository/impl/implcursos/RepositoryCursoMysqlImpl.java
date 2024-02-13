package com.proyectito.repository.impl.implcursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectito.repository.RepositoryCurso;
import com.proyectito.repository.models.Curso;
import com.proyectito.utils.TextStyles;
import com.proyectito.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryCursoMysqlImpl implements RepositoryCurso {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Curso> listar() {
        List<Curso> listCursoes = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM courses")) {
            while (rs.next()) {
                listCursoes.add(crearCurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCursoes;
    }

    @Override
    public Curso porId(int ID) {
        Curso Curso = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses WHERE id_course=?")) {
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso = crearCurso(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Curso;
    }


    @Override
    public void crear(Curso curso) {
        String sql = "INSERT INTO courses(name_course,course_guide) VALUES(?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getGuiaCatedra());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            TextStyles.msgError(throwables.getMessage());
        }
    }

    @Override
    public void editar(Curso curso, int id) {
        String sql = "UPDATE courses SET name_course = ?,course_guide = ? WHERE id_course=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getGuiaCatedra());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Curso curso, int id) {
        String sql = "DELETE FROM courses WHERE id_course=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Curso crearCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setNombre(rs.getString("name_course"));
        curso.setGuiaCatedra(rs.getString("course_guide"));
        return curso;
    }


    
}
