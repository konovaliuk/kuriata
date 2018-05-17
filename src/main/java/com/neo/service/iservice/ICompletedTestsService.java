package com.neo.service.iservice;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.exceptions.ServiceException;

import java.util.List;

public interface ICompletedTestsService extends IService {
//    List<CompleteTest> getAllCompletedTestsPaginated(int pageNumber, int perPage)throws ServiceException;

    List<CompleteTest> getAllCompletedTests() throws ServiceException;

    List<CompleteTest> getAllCompletedTestsByStudent(Student student) throws ServiceException;

    boolean deleteComletedTest(CompleteTest completedTest) throws ServiceException;

    boolean deleteAllCompletedTestsByStudent(Student student) throws ServiceException;
}
