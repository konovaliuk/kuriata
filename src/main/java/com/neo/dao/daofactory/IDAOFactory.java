package com.neo.dao.daofactory;

import com.neo.exceptions.DAOException;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.*;

public interface IDAOFactory {
    IAdminsDAO getAdminsDAO(WrappedConnection wrappedConnector) throws DAOException;

    IStudentsDAO getStudentsDAO(WrappedConnection wrappedConnector) throws DAOException;

    IQuestionsDAO getQuestionsDAO(WrappedConnection wrappedConnector) throws DAOException;

    IAnswersDAO getAnswersDAO(WrappedConnection wrappedConnector) throws DAOException;

    ICompleteTestsDAO getCompleteTestsDAO(WrappedConnection wrappedConnector) throws DAOException;
}
