package com.neo.dao.connection;

import com.neo.exceptions.DAOException;

public interface IConnectionFactory {
    WrappedConnection getConnection() throws DAOException;
    void closeConnection() throws DAOException;
}
