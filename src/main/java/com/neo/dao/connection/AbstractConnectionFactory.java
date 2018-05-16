package com.neo.dao.connection;

import com.neo.exceptions.DAOException;

public abstract class AbstractConnectionFactory {
    //ToDo: uncomment below

//    private static SupportedConnectionTypes currentType = SupportedConnectionTypes.JDBC;
//
//    public static IConnectionFactory getDAOFactory() throws DAOException {
//        IConnectionFactory connectionFactory = null;
//        try {
//            switch (currentType) {
//                case JDBC:
//                    connectionFactory = new JDBCConnectionFactory();
//                    break;
//                default:
//                    throw new RuntimeException("Connection type= " + currentType + " not supported");
//            }
//        }catch (Throwable t){
//            throw new DAOException("Exception in connection factory initialization.", t );
//        }
//        return connectionFactory;
//    }

}
