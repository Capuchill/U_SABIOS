package com.proyectito.repository.impl.implProfesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectito.exceptiones.ErrorU;
import com.proyectito.repository.RepositoryProfesor;
import com.proyectito.repository.models.Profesor;
import com.proyectito.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProfesorMysqlImpl implements RepositoryProfesor{

      private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }


    @Override
    public void crearProfesor(Profesor profesor) {
        String query = "INSERT INTO people (dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex, rol) " +
        "VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, profesor.getDniType());
            stmt.setLong(2, profesor.getDniNumber());
            stmt.setString(3, profesor.getPName());
            stmt.setString(4, profesor.getSName());
            stmt.setString(5, profesor.getPLastName());
            stmt.setString(6, profesor.getSLastName());
            stmt.setString(7, profesor.getResidingCity());
            stmt.setString(8, profesor.getAddressHome());
            stmt.setString(9, profesor.getPhone());
            stmt.setString(10, profesor.getBornDate());
            stmt.setString(11, profesor.getSex());
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
    public void actualizarProfesor(Profesor profesor) {
        String query = "UPDATE people SET dni_type = ?, p_name = ?, s_name = ?, p_lastName = ?, s_lastName = ?, residing_city = ?, address_home = ?, phone = ? WHERE id_person = ?";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, profesor.getDniType());
            stmt.setString(2, profesor.getPName());
            stmt.setString(3, profesor.getSName());
            stmt.setString(4, profesor.getPLastName());
            stmt.setString(5, profesor.getSLastName());
            stmt.setString(6, profesor.getResidingCity());
            stmt.setString(7, profesor.getAddressHome());
            stmt.setString(8, profesor.getPhone());
            stmt.setInt(9, profesor.getIdPerson());
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
    public List<Profesor> buscarProfesors() {
        List<Profesor> Profesors = null;

        try (Connection conect = getConnection();
            Statement stmt = conect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM people WHERE rol = 'Teacher'");) {

            Profesors = new ArrayList<>();

            while (rs.next()) {
                Profesors.add(crearProfesors(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Profesors;
    }

    @Override
    public void elimiarProfesor(Profesor profesor) {
        String query = "DELETE FROM people WHERE id_person = ?";
        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query);
        ) {

            stmt.setInt(1, profesor.getIdPerson());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profesor buscarDocumProfesor(long documento) {
        String query = "SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM people WHERE rol = 'Student' AND dni_number = ?";

        Profesor Profesor = null;

        try (
                Connection conect = getConnection();
                PreparedStatement stmt = conect.prepareStatement(query);
            ) {

                stmt.setLong(1, documento);

                try (ResultSet res = stmt.executeQuery()) {
                    if(res.next()) {
                        Profesor = crearProfesors(res);
                    }
                    else {}

                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Profesor;

    }

    private Profesor crearProfesor(ResultSet res) throws SQLException {
        Profesor Profesor = new Profesor(
            
            res.getString(1),
            res.getLong(2),
            res.getString(3),
            res.getString(4),
            res.getString(5),
            res.getString(6)
        );
        
        Profesor.setInfoAdicional(
            res.getString(7),
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11)
        );
    
        return Profesor;
        
    }

    private Profesor crearProfesors(ResultSet res) throws SQLException {
        Profesor profesor = new Profesor(
            res.getInt(1),
            res.getString(2),
            res.getLong(3),
            res.getString(4),
            res.getString(5),
            res.getString(6),
            res.getString(7)
        );
        
        profesor.setInfoAdicional(
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11),
            res.getString(12)
        );
    
        return profesor;
        
    }
    
}
