package com.neo.dao.mysqldao;

import com.neo.beans.Student;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.IStudentsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements IStudentsDAO {
    private WrappedConnection wrappedConnector;

    public static final String STUDENTS_TABLE_NAME = "students";
    public static final String SQL_FIND_ALL_STUDENTS = "SELECT * FROM " + STUDENTS_TABLE_NAME;
    public static final String SQL_FIND_STUDENT_BY_ID = "SELECT * FROM " + STUDENTS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_FIND_STUDENT_BY_LASTNAME = "SELECT * FROM " + STUDENTS_TABLE_NAME + " WHERE lastName LIKE ?";
    public static final String SQL_FIND_STUDENT_BY_RATING = "SELECT * FROM " + STUDENTS_TABLE_NAME + " WHERE rating";
    public static final String SQL_FIND_STUDENT_BY_EMAIL = "SELECT * FROM " + STUDENTS_TABLE_NAME + " WHERE email = ?";
    public static final String SQL_INSERT_STUDENT = "INSERT INTO " + STUDENTS_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_STUDENT = "UPDATE " + STUDENTS_TABLE_NAME + " SET firstName = ?, lastName = ?, email = ?, rating = ? WHERE id = ?";
    public static final String SQL_DELETE_STUDENT_BY_ID = "DELETE FROM " + STUDENTS_TABLE_NAME + " WHERE id = ?";

    public StudentsDAO(WrappedConnection wrappedConnector) {
        this.wrappedConnector = wrappedConnector;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (/*Connection cn = ConnectionPool.getConnection(); */Statement st = /*cn.createStatement()*/ wrappedConnector.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_FIND_ALL_STUDENTS);
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getDouble("rating")));
            }
        } catch (SQLException e) {
            //ToDo: change next line to logging via log4j
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            //maybe close needed returning connection to pool
        }
        return students;
    }

    public Student findById(int id) {
        try (final PreparedStatement pSt = this.wrappedConnector.prepareStatement(SQL_FIND_STUDENT_BY_ID)) {
            pSt.setInt(1, id);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Student(rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getDouble("rating"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Student %s does not exists", id));
    }

    @Override
    public int create(Student student) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_INSERT_STUDENT)) {
            //pSt.setInt(1, student.getId());
            pSt.setString(2, student.getFirstName());
            pSt.setString(3, student.getLastName());
            pSt.setString(4, student.getEmail());
            pSt.setDouble(5, student.getRating());
            pSt.executeUpdate();
            try (ResultSet generatedKeys = pSt.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not add student to DB");
    }

    public boolean deleteById(int id) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_DELETE_STUDENT_BY_ID)) {
            pSt.setInt(1, id);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean update(Student student) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_UPDATE_STUDENT)) {
            pSt.setString(1, student.getFirstName());
            pSt.setString(2, student.getLastName());
            pSt.setString(3, student.getEmail());
            pSt.setDouble(4, student.getRating());
            pSt.setInt(5, student.getId());
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //порівняння зробити через ЕNUM який повертає значення "Greater Than", "Equal To", "Less Than"
    public List<Student> findByRating(RatingComparingConstant ratingComparingConstant, double... rating) {
        List<Student> result = new ArrayList<>();
        try (PreparedStatement prepSt = wrappedConnector.prepareStatement(SQL_FIND_STUDENT_BY_RATING + ratingComparingConstant
                //ToDo: make understandable comment
        )) {
            //ToDo: change if from rating checking to checking firsMethodParameter, that should me ENUM this selection may me changed to switch/case
            switch (ratingComparingConstant) {
                case RATING_BETWEEN:
                    prepSt.setDouble(1, rating[0]);
                    prepSt.setDouble(2, rating[1]);
                    break;
                default:
                    prepSt.setDouble(1, rating[0]);
            }
            ResultSet resultSet = prepSt.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                //ToDo: realize via constructor with parameters
                result.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getDouble("rating")));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            //ToDo: change next line to logging via log4j
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            //maybe close needed returning connection to pool
        }
        return result;
    }

    public List<Student> findByLastName(String lastName) {
        List<Student> result = new ArrayList<>();
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_FIND_STUDENT_BY_LASTNAME)) {
            pSt.setString(1, lastName);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()) {
                result.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getDouble("rating")));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            //ToDo: change next line to logging via log4j
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            //maybe close needed returning connection to pool
        }
        return result;
    }

    @Override
    public Student login(String studentEmail) {
        try (final PreparedStatement pSt = this.wrappedConnector.prepareStatement(SQL_FIND_STUDENT_BY_EMAIL)) {
            pSt.setString(1, studentEmail);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Student(rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getDouble("rating"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
//        throw new IllegalStateException(String.format("Student with email \"%s\" does not exists", studentEmail));
    }
}
