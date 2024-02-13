package com.unisabio.repository.repo_mysql.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.unisabio.conexiones_bd.admin_conects.mysql.MysqlConectBD;
import com.unisabio.exceptions.errors.ErrorU;
import com.unisabio.repository.RepositoryTeacher;
import com.unisabio.repository.models.models_extends.Teacher;

public class RepoTeacherImpl implements RepositoryTeacher {

    private Connection getConnection() throws SQLException {
        return MysqlConectBD.getInstance();
    }

    @Override
    public void crearTeacher(Teacher teacher) {
        String query = "INSERT INTO PEOPLE (dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex, rol) " +
        "VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, teacher.getDniType());
            stmt.setLong(2, teacher.getDniNumber());
            stmt.setString(3, teacher.getPName());
            stmt.setString(4, teacher.getSName());
            stmt.setString(5, teacher.getPLastName());
            stmt.setString(6, teacher.getSLastName());
            stmt.setString(7, teacher.getResidingCity());
            stmt.setString(8, teacher.getAddressHome());
            stmt.setString(9, teacher.getPhone());
            stmt.setString(10, teacher.getBornDate());
            stmt.setString(11, teacher.getSex());
            stmt.setInt(12, 1);
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
    public void actualizarTeacher(Teacher teacher) {
        String query = "UPDATE PEOPLE SET dni_type = ?, p_name = ?, s_name = ?, p_lastName = ?, s_lastName = ?, residing_city = ?, address_home = ?, phone = ? WHERE id_person = ?";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, teacher.getDniType());
            stmt.setString(2, teacher.getPName());
            stmt.setString(3, teacher.getSName());
            stmt.setString(4, teacher.getPLastName());
            stmt.setString(5, teacher.getSLastName());
            stmt.setString(6, teacher.getResidingCity());
            stmt.setString(7, teacher.getAddressHome());
            stmt.setString(8, teacher.getPhone());
            stmt.setInt(9, teacher.getIdPerson());
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
    public List<Teacher> buscarTeachers() {
        List<Teacher> teacher = null;

        try (Connection conect = getConnection();
            Statement stmt = conect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE rol = 'Teacher'");) {

            teacher = new ArrayList<>();

            while (rs.next()) {
                teacher.add(crearTeachers(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    @Override
    public void elimiarTeacher(Teacher teacher) {
        String query = "DELETE FROM PEOPLE WHERE id_person = ?";
        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query);
        ) {

            stmt.setInt(1, teacher.getIdPerson());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher buscarDocumTeacher(long documento) {
        String query = "SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE rol = 'Teacher' AND dni_number = ?";

        Teacher teacher = null;

        try (
                Connection conect = getConnection();
                PreparedStatement stmt = conect.prepareStatement(query);
            ) {

                stmt.setLong(1, documento);

                try (ResultSet res = stmt.executeQuery()) {
                    if(res.next()) {
                        teacher = crearTeachers(res);
                    }
                    else {}

                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;

    }

    private Teacher crearTeacher(ResultSet res) throws SQLException {
        Teacher teacher = new Teacher(
            
            res.getString(1),
            res.getLong(2),
            res.getString(3),
            res.getString(4),
            res.getString(5),
            res.getString(6)
        );
        
        teacher.setInfoAdicional(
            res.getString(7),
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11)
        );
    
        return teacher;
        
    }

    private Teacher crearTeachers(ResultSet res) throws SQLException {
        Teacher teacher = new Teacher(
            res.getInt(1),
            res.getString(2),
            res.getLong(3),
            res.getString(4),
            res.getString(5),
            res.getString(6),
            res.getString(7)
        );
        
        teacher.setInfoAdicional(
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11),
            res.getString(12)
        );
    
        return teacher;
        
    }
    
}

