package com.neo.service.iservice;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;

import java.util.List;

public interface ICompletedTestsService extends IService {
    List<CompleteTest> getAllCompletedTestsPaginated(int pageNumber, int perPage);

    List<CompleteTest> getAllCompletedTestsByStudent(Student student);

    boolean deleteComletedTest(CompleteTest completedTest);

    boolean deleteAllCompletedTestsByStudent(Student student);
}
