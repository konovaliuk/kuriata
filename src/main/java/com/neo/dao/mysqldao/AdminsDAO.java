package com.neo.dao.mysqldao;

import com.neo.beans.Admin;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.IAdminsDAO;
import com.neo.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminsDAO implements IAdminsDAO {
    public static final String ADMINS_TABLE_NAME = "admins";
    public static final String SQL_SELECT_ALL_ADMINS = "SELECT * FROM " + ADMINS_TABLE_NAME;
    public static final String SQL_SELECT_ADMIN_BY_ID = "SELECT * FROM " + ADMINS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_SELECT_ADMIN_BY_NAME_AND_PASSWORD = "SELECT * FROM " + ADMINS_TABLE_NAME + " WHERE name = ?, password = ?";
    public static final String SQL_INSERT_ADMIN = "INSERT INTO " + ADMINS_TABLE_NAME + " VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_ADMIN = "UPDATE " + ADMINS_TABLE_NAME + " SET name = ?, password = ? WHERE id = ?";
    public static final String SQL_DELETE_ADMIN_BY_ID = "DELETE FROM " + ADMINS_TABLE_NAME + " WHERE id = ?";

    private WrappedConnection wrappedConnection;

    public AdminsDAO(WrappedConnection wrappedConnection) {
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
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try (/*Connection cn = ConnectionPool.getConnection();*/ Statement st = /*cn.createStatement()*/ wrappedConnection.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_ADMINS);
            while (rs.next()) {
                admins.add(new Admin(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            //ToDo: change next line to logging via log4j
            //e.printStackTrace();
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            //super.closeStatement(st);
            //close needed returning connection to pool
        }
        return admins;
    }

    @Override
    public Admin findById(int id) {
        try (final PreparedStatement pSt = this.wrappedConnection.prepareStatement(SQL_SELECT_ADMIN_BY_ID)) {
            pSt.setInt(1, id);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Admin(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ToDO: change exception type to DAOException
        throw new IllegalStateException(String.format("Admin %s does not exists", id));
    }

    //ToDo: think about renaming method to add
    @Override
    public int create(Admin admin) {
        try (final PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_INSERT_ADMIN)) {
            //pSt.setInt(1, admin.getId());
            pSt.setString(2, admin.getName());
            pSt.setString(3, admin.getPassword());
            pSt.executeUpdate();
            try (ResultSet generatedKeys = pSt.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not add admin to DB");
    }

    public boolean deleteById(int id) {
        try (PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_DELETE_ADMIN_BY_ID)) {
            pSt.setInt(1, id);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Admin admin) throws DAOException{
        try (PreparedStatement pSt = wrappedConnection.prepareStatement(SQL_UPDATE_ADMIN)) {
            pSt.setString(1, admin.getName());
            pSt.setString(2, admin.getPassword());
            pSt.setInt(3, admin.getId());
            pSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Can't update admin="+admin+" in DB.", e );
        }
    }

    @Override
    public Admin login(String adminUserName, String adminPassword) {
        try (final PreparedStatement pSt = this.wrappedConnection.prepareStatement(SQL_SELECT_ADMIN_BY_NAME_AND_PASSWORD)) {
            pSt.setString(1, adminUserName);
            pSt.setString(2, adminPassword);
            try (final ResultSet rs = pSt.executeQuery()) {
                while (rs.next()) {
                    return new Admin(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //throw new IllegalStateException(String.format("Admin with name: %s and password: %s does not exists", adminUserName, adminPassword));
        return null;
    }
}


