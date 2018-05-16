package com.neo.dao.mysqldao;

import com.neo.beans.Question;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.IQuestionsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO implements IQuestionsDAO {
    private WrappedConnection wrappedConnector;

    public static final String QIESTIONS_TABLE_NAME = "questions";
    public static final String SQL_SELECT_ALL_QIESTIONS = "SELECT * FROM " + QIESTIONS_TABLE_NAME;
    public static final String SQL_SELECT_QIESTION_BY_ID = "SELECT * FROM " + QIESTIONS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_QIESTION = "INSERT INTO " + QIESTIONS_TABLE_NAME + " (title, imagePath, text, item_1, item_2, item_3, item_4) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_QIESTION = "UPDATE " + QIESTIONS_TABLE_NAME + " SET title = ?, text = ?, item1 = ?, item2 = ?, item3 = ?, item4 = ? WHERE id = ?";
    public static final String SQL_DELETE_QIESTION_BY_ID = "DELETE FROM " + QIESTIONS_TABLE_NAME + " WHERE id = ?";

    public QuestionsDAO(WrappedConnection wrappedConnector){
        this.wrappedConnector = wrappedConnector;
    }


    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        try (/*Connection cn = ConnectionPool.getConnection();*/ Statement st = /*cn.createStatement()*/ wrappedConnector.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_QIESTIONS);
            while (rs.next()) {
                questions.add(new Question(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getString("item1"),
                        rs.getString("item2"),
                        rs.getString("item3"),
                        rs.getString("item4")));
            }
        } catch (SQLException e) {
            //ToDo: change next line to logging via log4j
            //e.printStackTrace();
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            //super.closeStatement(st);
            //close needed returning connection to pool
        }
        return questions;
    }

    @Override
    public Question findById(int id) {
        try (final PreparedStatement pSt = this.wrappedConnector.prepareStatement(SQL_DELETE_QIESTION_BY_ID)) {
            pSt.setInt(1, id);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Question(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("text"),
                            rs.getString("item1"),
                            rs.getString("item2"),
                            rs.getString("item3"),
                            rs.getString("item4"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Question %s does not exists", id));
    }

    @Override
    public int create(Question question) {
        try (final PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_INSERT_QIESTION)) {
            pSt.setString(1, question.getTitle());
            pSt.setString(2, question.getText());
            pSt.setString(3, question.getItem1());
            pSt.setString(4, question.getItem2());
            pSt.setString(5, question.getItem3());
            pSt.setString(6, question.getItem4());
            pSt.executeUpdate();
            try (ResultSet generatedKeys = pSt.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not add question to DB");
    }

    @Override
    public boolean update(Question question) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_UPDATE_QIESTION)) {
            pSt.setString(1, question.getTitle());
            pSt.setString(2, question.getText());
            pSt.setString(3, question.getItem1());
            pSt.setString(4, question.getItem2());
            pSt.setString(5, question.getItem3());
            pSt.setString(6, question.getItem4());
            pSt.setInt(7, question.getId());
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_DELETE_QIESTION_BY_ID)) {
            pSt.setInt(1, id);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
