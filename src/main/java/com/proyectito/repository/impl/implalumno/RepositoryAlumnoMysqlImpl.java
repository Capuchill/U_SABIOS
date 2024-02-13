package com.proyectito.repository.impl.implalumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectito.exceptiones.ErrorU;
import com.proyectito.repository.RepositoryAlumno;
import com.proyectito.repository.models.Alumno;
import com.proyectito.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAlumnoMysqlImpl implements RepositoryAlumno{
    
     private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }


    @Override
    public void crearAlumno(Alumno alumno) {
        String query = "INSERT INTO people (dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex, rol) " +
        "VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, alumno.getDniType());
            stmt.setLong(2, alumno.getDniNumber());
            stmt.setString(3, alumno.getPName());
            stmt.setString(4, alumno.getSName());
            stmt.setString(5, alumno.getPLastName());
            stmt.setString(6, alumno.getSLastName());
            stmt.setString(7, alumno.getResidingCity());
            stmt.setString(8, alumno.getAddressHome());
            stmt.setString(9, alumno.getPhone());
            stmt.setString(10, alumno.getBornDate());
            stmt.setString(11, alumno.getSex());
            stmt.setInt(12, 2);
            stmt.executeUpdate();
            
        } 
        catch (SQLIntegrityConstraintViolationException dup) {
            ErrorU.errDocDup();
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        String query = "UPDATE people SET dni_type = ?, p_name = ?, s_name = ?, p_lastName = ?, s_lastName = ?, residing_city = ?, address_home = ?, phone = ? WHERE id_person = ?";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, alumno.getDniType());
            stmt.setString(2, alumno.getPName());
            stmt.setString(3, alumno.getSName());
            stmt.setString(4, alumno.getPLastName());
            stmt.setString(5, alumno.getSLastName());
            stmt.setString(6, alumno.getResidingCity());
            stmt.setString(7, alumno.getAddressHome());
            stmt.setString(8, alumno.getPhone());
            stmt.setInt(9, alumno.getIdPerson());
            stmt.executeUpdate();
            
        } 
        catch (SQLIntegrityConstraintViolationException dup) {
            ErrorU.errDocDup();
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        List<Alumno> alumnos = null;

        try (Connection conect = getConnection();
            Statement stmt = conect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM people WHERE rol = 'Student'");) {

            alumnos = new ArrayList<>();

            while (rs.next()) {
                alumnos.add(crearAlumnos(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    @Override
    public void elimiarAlumno(Alumno alumno) {
        String query = "DELETE FROM people WHERE id_person = ?";
        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query);
        ) {

            stmt.setInt(1, alumno.getIdPerson());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alumno buscarDocumAlumno(long documento) {
        String query = "SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM people WHERE rol = 'Student' AND dni_number = ?";

        Alumno alumno = null;

        try (
                Connection conect = getConnection();
                PreparedStatement stmt = conect.prepareStatement(query);
            ) {

                stmt.setLong(1, documento);

                try (ResultSet res = stmt.executeQuery()) {
                    if(res.next()) {
                        alumno = crearAlumnos(res);
                    }
                    else {}

                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumno;

    }

    private Alumno crearAlumno(ResultSet res) throws SQLException {
        Alumno alumno = new Alumno(
            
            res.getString(1),
            res.getLong(2),
            res.getString(3),
            res.getString(4),
            res.getString(5),
            res.getString(6)
        );
        
        alumno.setInfoAdicional(
            res.getString(7),
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11)
        );
    
        return alumno;
        
    }

    private Alumno crearAlumnos(ResultSet res) throws SQLException {
        Alumno alumno = new Alumno(
            res.getInt(1),
            res.getString(2),
            res.getLong(3),
            res.getString(4),
            res.getString(5),
            res.getString(6),
            res.getString(7)
        );
        
        alumno.setInfoAdicional(
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11),
            res.getString(12)
        );
    
        return alumno;
        
    }
}
