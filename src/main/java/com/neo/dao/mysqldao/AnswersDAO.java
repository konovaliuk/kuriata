package com.neo.dao.mysqldao;

import com.neo.exceptions.DAOException;
import com.neo.beans.Answer;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.IAnswersDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswersDAO implements IAnswersDAO {
    public static final String ANSWERS_TABLE_NAME = "answers";

    public static final String SQL_SELECT_ALL_ADMINS = "SELECT * FROM " + ANSWERS_TABLE_NAME;

    public static final String SQL_SELECT_ANSWER_BY_ID = "SELECT * FROM " + ANSWERS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_ANSWER = "INSERT INTO " + ANSWERS_TABLE_NAME + "(item_1, item_2, item_3, item_4) VALUES (?, ?, ?, ?)";

    public static final String SQL_UPDATE_ANSWER = "UPDATE " + ANSWERS_TABLE_NAME + " SET item_1 = ?, item_2 = ?, item_3 = ?, item_4 = ? WHERE id = ?";
    public static final String SQL_DELETE_ANSWER_BY_ID = "DELETE FROM " + ANSWERS_TABLE_NAME + " WHERE id = ?";

    private WrappedConnection wrappedConnector;

    public AnswersDAO(WrappedConnection wrappedConnector) {
        this.wrappedConnector = wrappedConnector;
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();
        try (Statement st = wrappedConnector.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_ADMINS);
            while (rs.next()) {
                answers.add(new Answer(rs.getInt("id"),
                        rs.getBoolean("item_1"),
                        rs.getBoolean("item_2"),
                        rs.getBoolean("item_3"),
                        rs.getBoolean("item_4")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public Answer findById(int id) {
        try (final PreparedStatement pSt = this.wrappedConnector.prepareStatement(SQL_SELECT_ANSWER_BY_ID)) {
            pSt.setInt(1, id);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Answer(rs.getInt("id"),
                            rs.getBoolean("item_1"),
                            rs.getBoolean("item_2"),
                            rs.getBoolean("item_3"),
                            rs.getBoolean("item_4"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Answer %s does not exists", id));
    }

    @Override
    public int create(Answer answer) {
        try (final PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_INSERT_ANSWER)) {
            pSt.setBoolean(1, answer.isItem1());
            pSt.setBoolean(2, answer.isItem2());
            pSt.setBoolean(3, answer.isItem3());
            pSt.setBoolean(4, answer.isItem4());
            pSt.executeUpdate();
            try (ResultSet generatedKeys = pSt.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not add answer to DB");
    }

    @Override
    public boolean update(Answer answer) throws DAOException {
        try (final PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_UPDATE_ANSWER)) {
            pSt.setBoolean(1, answer.isItem1());
            pSt.setBoolean(2, answer.isItem2());
            pSt.setBoolean(3, answer.isItem3());
            pSt.setBoolean(4, answer.isItem4());
            pSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Could not update answer in DB", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement pSt = wrappedConnector.prepareStatement(SQL_DELETE_ANSWER_BY_ID)) {
            pSt.setInt(1, id);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
