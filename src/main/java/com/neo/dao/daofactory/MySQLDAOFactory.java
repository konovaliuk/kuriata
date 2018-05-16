package com.neo.dao.daofactory;

import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.idao.*;
import com.neo.dao.mysqldao.*;

public class MySQLDAOFactory implements IDAOFactory {
    @Override
    public IAdminsDAO getAdminsDAO(WrappedConnection wrappedConnector) {
        return new AdminsDAO(wrappedConnector);
    }

    @Override
    public IStudentsDAO getStudentsDAO(WrappedConnection wrappedConnection) {
        return new StudentsDAO(wrappedConnection);
    }

    @Override
    public IQuestionsDAO getQuestionsDAO(WrappedConnection wrappedConnection) {
        return new QuestionsDAO(wrappedConnection);
    }

    @Override
    public IAnswersDAO getAnswersDAO(WrappedConnection wrappedConnection) {
        return new AnswersDAO(wrappedConnection);
    }

    @Override
    public ICompleteTestsDAO getCompleteTestsDAO(WrappedConnection wrappedConnection) {
        //ToDo: change method
        throw new UnsupportedOperationException();
    }
}
