package com.neo.dao.connection;

import com.neo.exceptions.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCConnectionFactory implements IConnectionFactory {
    protected DataSource dataSource;
    protected WrappedConnection connection;

    private JDBCConnectionFactory() throws DAOException{
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            dataSource = (DataSource)
                    envCtx.lookup("jdbc/libraryDB");
//            LOGGER.debug("DataSource initialized");
        }catch (Exception e){
            throw new DAOException("JDBCConnectionFactory exception while creating.", e);
        }
    }

    //ToDo: realize method
    @Override
    public void closeConnection() throws DAOException {
        throw new UnsupportedOperationException();
    }

    //ToDo: realize method
    @Override
    public WrappedConnection getConnection() throws DAOException {
        throw new UnsupportedOperationException();
    }
}
