package com.unisabio.repository.repo_mysql.student;

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
import com.unisabio.repository.RepositoryStudent;
import com.unisabio.repository.models.models_extends.Student;

public class RepoStudentImpl implements RepositoryStudent {

    private Connection getConnection() throws SQLException {
        return MysqlConectBD.getInstance();
    }

    @Override
    public void crearStudent(Student student) {
        String query = "INSERT INTO PEOPLE (dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex, rol) " +
        "VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, student.getDniType());
            stmt.setLong(2, student.getDniNumber());
            stmt.setString(3, student.getPName());
            stmt.setString(4, student.getSName());
            stmt.setString(5, student.getPLastName());
            stmt.setString(6, student.getSLastName());
            stmt.setString(7, student.getResidingCity());
            stmt.setString(8, student.getAddressHome());
            stmt.setString(9, student.getPhone());
            stmt.setString(10, student.getBornDate());
            stmt.setString(11, student.getSex());
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
    public void actualizarStudent(Student student) {
        String query = "UPDATE PEOPLE SET dni_type = ?, p_name = ?, s_name = ?, p_lastName = ?, s_lastName = ?, residing_city = ?, address_home = ?, phone = ? WHERE id_person = ?";

        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query)
        ) {
            stmt.setString(1, student.getDniType());
            stmt.setString(2, student.getPName());
            stmt.setString(3, student.getSName());
            stmt.setString(4, student.getPLastName());
            stmt.setString(5, student.getSLastName());
            stmt.setString(6, student.getResidingCity());
            stmt.setString(7, student.getAddressHome());
            stmt.setString(8, student.getPhone());
            stmt.setInt(9, student.getIdPerson());
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
    public List<Student> buscarStudents() {
        List<Student> students = null;

        try (Connection conect = getConnection();
            Statement stmt = conect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE rol = 'Student'");) {

            students = new ArrayList<>();

            while (rs.next()) {
                students.add(crearStudents(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public void elimiarStudent(Student student) {
        String query = "DELETE FROM PEOPLE WHERE id_person = ?";
        try (
            Connection conect = getConnection();
            PreparedStatement stmt = conect.prepareStatement(query);
        ) {

            stmt.setInt(1, student.getIdPerson());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student buscarDocumStudent(long documento) {
        String query = "SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE rol = 'Student' AND dni_number = ?";

        Student student = null;

        try (
                Connection conect = getConnection();
                PreparedStatement stmt = conect.prepareStatement(query);
            ) {

                stmt.setLong(1, documento);

                try (ResultSet res = stmt.executeQuery()) {
                    if(res.next()) {
                        student = crearStudents(res);
                    }
                    else {}

                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;

    }

    private Student crearStudent(ResultSet res) throws SQLException {
        Student student = new Student(
            
            res.getString(1),
            res.getLong(2),
            res.getString(3),
            res.getString(4),
            res.getString(5),
            res.getString(6)
        );
        
        student.setInfoAdicional(
            res.getString(7),
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11)
        );
    
        return student;
        
    }

    private Student crearStudents(ResultSet res) throws SQLException {
        Student student = new Student(
            res.getInt(1),
            res.getString(2),
            res.getLong(3),
            res.getString(4),
            res.getString(5),
            res.getString(6),
            res.getString(7)
        );
        
        student.setInfoAdicional(
            res.getString(8),
            res.getString(9),
            res.getString(10),
            res.getString(11),
            res.getString(12)
        );
    
        return student;
        
    }
    
}

