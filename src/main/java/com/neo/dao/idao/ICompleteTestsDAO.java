package com.neo.dao.idao;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.exceptions.DAOException;

import java.util.List;

public interface ICompleteTestsDAO extends IDAO<CompleteTest> {
    //ToDo: uncomment and realize methods
//    int getCompleteTestsCount() throws DAOException;
//
//    List<CompleteTest> findAllPaginated(int pageNumber, int perPage) throws DAOException;
//
//    void deleteAllCompletedTestsByStudent(Student student) throws DAOException;
//
    List<CompleteTest> findAllCompletedTestsByStudent(Student student) throws DAOException;
}
