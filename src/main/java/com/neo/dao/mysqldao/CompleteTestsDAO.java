package com.neo.dao.mysqldao;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.ICompleteTestsDAO;
import com.neo.exceptions.DAOException;
import com.neo.exceptions.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CompleteTestsDAO implements ICompleteTestsDAO {
    public static final String COMPLETE_TESTS_TABLE_NAME = "completed_tests";
    public static final String SQL_SELECT_ALL_COMPLETE_TESTS = "SELECT * FROM " + COMPLETE_TESTS_TABLE_NAME;
    public static final String SQL_SELECT_TEST_BY_STUDENT = "SELECT * FROM " + COMPLETE_TESTS_TABLE_NAME + " WHERE student_id = ?";
    public static final String SQL_SELECT_TEST_BY_ID = "SELECT * FROM " + COMPLETE_TESTS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_DELETE_ALL_TESTS_BY_STUDENT = "DELETE FROM " + COMPLETE_TESTS_TABLE_NAME + " WHERE studetn_id = ?";
    public static final String SQL_DELETE_TEST_BY_ID = "DELETE FROM " + COMPLETE_TESTS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_TEST = "INSERT INTO " + COMPLETE_TESTS_TABLE_NAME + " VALUES (?, ?, ?, ?" +
            "?, ?, ?, ?, ?," +    //question1: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question2: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question3: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question4: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question5: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question6: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question7: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question8: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question9: id, answ_1, answ_2, answ_3, answ_4
            "?, ?, ?, ?, ?," +    //question10: id, answ_1, answ_2, answ_3, answ_4
            ")";


    private WrappedConnection wrappedConnection;

    public CompleteTestsDAO(WrappedConnection wrappedConnection) {
//        Соединение с базой данных инициирует конструктор DAO, либо получает
//        его из пула. В методе остаются возможности по созданию экземпляра Statement
//        для выполнения запросов и его закрытию. В данной реализации использовался
//        класс-обертка для соединения, инкапсулирующий процесс создания соедине-
//        ния и упрощающий его использование. Такой подход при организации пула
//        соединений из экземпляров классов-оберток резко затрудняет попадание в пул
//        «диких» соединений, созданных программистом в обход пула.
        this.wrappedConnection = wrappedConnection;
    }

    @Override
    public List<CompleteTest> findAll() throws DAOException {
        List<CompleteTest> tests = new ArrayList<>();
        try (/*Connection cn = ConnectionPool.getConnection();*/ Statement st = /*cn.createStatement()*/ wrappedConnection.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_COMPLETE_TESTS);
            while (rs.next()) {
                tests.add(new CompleteTest(rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getDate("time"),
                        rs.getDouble("score"),
                        rs.getInt("q1_id"),
                        rs.getBoolean("q1_answ1"),
                        rs.getBoolean("q1_answ2"),
                        rs.getBoolean("q1_answ3"),
                        rs.getBoolean("q1_answ4"),

                        rs.getInt("q2_id"),
                        rs.getBoolean("q2_answ1"),
                        rs.getBoolean("q2_answ2"),
                        rs.getBoolean("q2_answ3"),
                        rs.getBoolean("q2_answ4"),

                        rs.getInt("q3_id"),
                        rs.getBoolean("q3_answ1"),
                        rs.getBoolean("q3_answ2"),
                        rs.getBoolean("q3_answ3"),
                        rs.getBoolean("q3_answ4"),

                        rs.getInt("q4_id"),
                        rs.getBoolean("q4_answ1"),
                        rs.getBoolean("q4_answ2"),
                        rs.getBoolean("q4_answ3"),
                        rs.getBoolean("q4_answ4"),

                        rs.getInt("q5_id"),
                        rs.getBoolean("q5_answ1"),
                        rs.getBoolean("q5_answ2"),
                        rs.getBoolean("q5_answ3"),
                        rs.getBoolean("q5_answ4"),

                        rs.getInt("q6_id"),
                        rs.getBoolean("q6_answ1"),
                        rs.getBoolean("q6_answ2"),
                        rs.getBoolean("q6_answ3"),
                        rs.getBoolean("q6_answ4"),

                        rs.getInt("q7_id"),
                        rs.getBoolean("q7_answ1"),
                        rs.getBoolean("q7_answ2"),
                        rs.getBoolean("q7_answ3"),
                        rs.getBoolean("q7_answ4"),

                        rs.getInt("q8_id"),
                        rs.getBoolean("q8_answ1"),
                        rs.getBoolean("q8_answ2"),
                        rs.getBoolean("q8_answ3"),
                        rs.getBoolean("q8_answ4"),

                        rs.getInt("q9_id"),
                        rs.getBoolean("q9_answ1"),
                        rs.getBoolean("q9_answ2"),
                        rs.getBoolean("q9_answ3"),
                        rs.getBoolean("q9_answ4"),

                        rs.getInt("q10_id"),
                        rs.getBoolean("q10_answ1"),
                        rs.getBoolean("q10_answ2"),
                        rs.getBoolean("q10_answ3"),
                        rs.getBoolean("q10_answ4")));
            }
        } catch (SQLException e) {
            //ToDo: change next line to logging via log4j
            e.printStackTrace();
            throw new DAOException("SQL exception (request or table failed): ", e);
        } finally {
            //super.closeStatement(st);
            //close needed returning connection to pool
        }
        return tests;
    }

    @Override
    public CompleteTest findById(int id) {
        try (final PreparedStatement pSt = this.wrappedConnection.prepareStatement(SQL_SELECT_TEST_BY_ID)) {
            pSt.setInt(1, id);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new CompleteTest(rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getDate("time"),
                            rs.getDouble("score"),
                            rs.getInt("q1_id"),
                            rs.getBoolean("q1_answ1"),
                            rs.getBoolean("q1_answ2"),
                            rs.getBoolean("q1_answ3"),
                            rs.getBoolean("q1_answ4"),

                            rs.getInt("q2_id"),
                            rs.getBoolean("q2_answ1"),
                            rs.getBoolean("q2_answ2"),
                            rs.getBoolean("q2_answ3"),
                            rs.getBoolean("q2_answ4"),

                            rs.getInt("q3_id"),
                            rs.getBoolean("q3_answ1"),
                            rs.getBoolean("q3_answ2"),
                            rs.getBoolean("q3_answ3"),
                            rs.getBoolean("q3_answ4"),

                            rs.getInt("q4_id"),
                            rs.getBoolean("q4_answ1"),
                            rs.getBoolean("q4_answ2"),
                            rs.getBoolean("q4_answ3"),
                            rs.getBoolean("q4_answ4"),

                            rs.getInt("q5_id"),
                            rs.getBoolean("q5_answ1"),
                            rs.getBoolean("q5_answ2"),
                            rs.getBoolean("q5_answ3"),
                            rs.getBoolean("q5_answ4"),

                            rs.getInt("q6_id"),
                            rs.getBoolean("q6_answ1"),
                            rs.getBoolean("q6_answ2"),
                            rs.getBoolean("q6_answ3"),
                            rs.getBoolean("q6_answ4"),

                            rs.getInt("q7_id"),
                            rs.getBoolean("q7_answ1"),
                            rs.getBoolean("q7_answ2"),
                            rs.getBoolean("q7_answ3"),
                            rs.getBoolean("q7_answ4"),

                            rs.getInt("q8_id"),
                            rs.getBoolean("q8_answ1"),
                            rs.getBoolean("q8_answ2"),
                            rs.getBoolean("q8_answ3"),
                            rs.getBoolean("q8_answ4"),

                            rs.getInt("q9_id"),
                            rs.getBoolean("q9_answ1"),
                            rs.getBoolean("q9_answ2"),
                            rs.getBoolean("q9_answ3"),
                            rs.getBoolean("q9_answ4"),

                            rs.getInt("q10_id"),
                            rs.getBoolean("q10_answ1"),
                            rs.getBoolean("q10_answ2"),
                            rs.getBoolean("q10_answ3"),
                            rs.getBoolean("q10_answ4"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Admin %s does not exists", id));
    }

    @Override
    public int create(CompleteTest test) throws DAOException, ServiceException {
        try (final PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_INSERT_TEST)) {
            pSt.setInt(2, test.getStudentId());
            pSt.setDate(3, test.getDate());
            pSt.setDouble(4, test.getScore());

            pSt.setInt(5, test.getQ1_id());
            pSt.setBoolean(6, test.isQ1_answ1());
            pSt.setBoolean(7, test.isQ1_answ2());
            pSt.setBoolean(8, test.isQ1_answ3());

            pSt.setBoolean(9, test.isQ1_answ4());
            pSt.setBoolean(11, test.isQ2_answ1());
            pSt.setInt(10, test.getQ2_id());
            pSt.setBoolean(12, test.isQ2_answ2());
            pSt.setBoolean(13, test.isQ2_answ3());
            pSt.setBoolean(14, test.isQ2_answ4());

            pSt.setInt(10, test.getQ3_id());
            pSt.setBoolean(11, test.isQ3_answ1());
            pSt.setBoolean(12, test.isQ3_answ2());
            pSt.setBoolean(13, test.isQ3_answ3());
            pSt.setBoolean(14, test.isQ3_answ4());

            pSt.setInt(10, test.getQ4_id());
            pSt.setBoolean(11, test.isQ4_answ1());
            pSt.setBoolean(12, test.isQ4_answ2());
            pSt.setBoolean(13, test.isQ4_answ3());
            pSt.setBoolean(14, test.isQ4_answ4());

            pSt.setInt(10, test.getQ5_id());
            pSt.setBoolean(11, test.isQ5_answ1());
            pSt.setBoolean(12, test.isQ5_answ2());
            pSt.setBoolean(13, test.isQ5_answ3());
            pSt.setBoolean(14, test.isQ5_answ4());

            pSt.setInt(10, test.getQ6_id());
            pSt.setBoolean(11, test.isQ6_answ1());
            pSt.setBoolean(12, test.isQ6_answ2());
            pSt.setBoolean(13, test.isQ6_answ3());
            pSt.setBoolean(14, test.isQ6_answ4());

            pSt.setInt(10, test.getQ7_id());
            pSt.setBoolean(11, test.isQ7_answ1());
            pSt.setBoolean(12, test.isQ7_answ2());
            pSt.setBoolean(13, test.isQ7_answ3());
            pSt.setBoolean(14, test.isQ7_answ4());

            pSt.setInt(10, test.getQ8_id());
            pSt.setBoolean(11, test.isQ8_answ1());
            pSt.setBoolean(12, test.isQ8_answ2());
            pSt.setBoolean(13, test.isQ8_answ3());
            pSt.setBoolean(14, test.isQ8_answ4());

            pSt.setInt(10, test.getQ9_id());
            pSt.setBoolean(11, test.isQ9_answ1());
            pSt.setBoolean(12, test.isQ9_answ2());
            pSt.setBoolean(13, test.isQ9_answ3());
            pSt.setBoolean(14, test.isQ9_answ4());

            pSt.setInt(10, test.getQ10_id());
            pSt.setBoolean(11, test.isQ10_answ1());
            pSt.setBoolean(12, test.isQ10_answ2());
            pSt.setBoolean(13, test.isQ10_answ3());
            pSt.setBoolean(14, test.isQ10_answ4());

            pSt.executeUpdate();

            try (ResultSet generatedKeys = pSt.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new ServiceException("Could not add test to DB", e);
        }
        throw new IllegalStateException("Could not add test to DB");
    }

    @Override
    public boolean update(CompleteTest entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        try (PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_DELETE_TEST_BY_ID)) {
            pSt.setInt(1, id);
            pSt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cant delete test by id = " + id, e);
        }
        return true;
    }

    public boolean deleteByStudent(int studentId) throws DAOException {
        try (PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_DELETE_ALL_TESTS_BY_STUDENT)) {
            pSt.setInt(1, studentId);
            pSt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cant delete test by student with id = " + studentId, e);
        }
        return true;
    }

    public List<CompleteTest> findAllCompletedTestsByStudent(int studentId) throws DAOException {
        List<CompleteTest> tests = new ArrayList<>();
        try (final PreparedStatement pSt = this.wrappedConnection.prepareStatement(SQL_SELECT_TEST_BY_STUDENT)) {
            pSt.setInt(1, studentId);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    tests.add(new CompleteTest(rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getDate("time"),
                            rs.getDouble("score"),
                            rs.getInt("q1_id"),
                            rs.getBoolean("q1_answ1"),
                            rs.getBoolean("q1_answ2"),
                            rs.getBoolean("q1_answ3"),
                            rs.getBoolean("q1_answ4"),

                            rs.getInt("q2_id"),
                            rs.getBoolean("q2_answ1"),
                            rs.getBoolean("q2_answ2"),
                            rs.getBoolean("q2_answ3"),
                            rs.getBoolean("q2_answ4"),

                            rs.getInt("q3_id"),
                            rs.getBoolean("q3_answ1"),
                            rs.getBoolean("q3_answ2"),
                            rs.getBoolean("q3_answ3"),
                            rs.getBoolean("q3_answ4"),

                            rs.getInt("q4_id"),
                            rs.getBoolean("q4_answ1"),
                            rs.getBoolean("q4_answ2"),
                            rs.getBoolean("q4_answ3"),
                            rs.getBoolean("q4_answ4"),

                            rs.getInt("q5_id"),
                            rs.getBoolean("q5_answ1"),
                            rs.getBoolean("q5_answ2"),
                            rs.getBoolean("q5_answ3"),
                            rs.getBoolean("q5_answ4"),

                            rs.getInt("q6_id"),
                            rs.getBoolean("q6_answ1"),
                            rs.getBoolean("q6_answ2"),
                            rs.getBoolean("q6_answ3"),
                            rs.getBoolean("q6_answ4"),

                            rs.getInt("q7_id"),
                            rs.getBoolean("q7_answ1"),
                            rs.getBoolean("q7_answ2"),
                            rs.getBoolean("q7_answ3"),
                            rs.getBoolean("q7_answ4"),

                            rs.getInt("q8_id"),
                            rs.getBoolean("q8_answ1"),
                            rs.getBoolean("q8_answ2"),
                            rs.getBoolean("q8_answ3"),
                            rs.getBoolean("q8_answ4"),

                            rs.getInt("q9_id"),
                            rs.getBoolean("q9_answ1"),
                            rs.getBoolean("q9_answ2"),
                            rs.getBoolean("q9_answ3"),
                            rs.getBoolean("q9_answ4"),

                            rs.getInt("q10_id"),
                            rs.getBoolean("q10_answ1"),
                            rs.getBoolean("q10_answ2"),
                            rs.getBoolean("q10_answ3"),
                            rs.getBoolean("q10_answ4")));
                }
            }
        } catch (SQLException e) {
            //ToDo: delete stacktrace
            e.printStackTrace();
            throw new DAOException("Can't extract all complete tests by student with id =" + studentId, e);
        }
        return tests;
    }
}
