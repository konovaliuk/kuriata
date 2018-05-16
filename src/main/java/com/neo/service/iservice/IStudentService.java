package com.neo.service.iservice;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.exceptions.ServiceException;

import java.util.List;

public interface IStudentService extends IService {
    List<Student> getAllStudents() throws ServiceException;

    //ToDo: uncomment for realization
    //List<Student> getAllStudentsPaginated(int pageNumber, int perPage);

    Student getStudentByID(int id) throws ServiceException;

    Student loginStudent(String email) throws ServiceException;

    List<CompleteTest> getStudentCompletedTests(Student student) throws ServiceException;

    int addStudent(Student student) throws ServiceException;

    boolean updateStudent(Student student) throws ServiceException;

    boolean deleteStudent(Student student) throws ServiceException;
}
