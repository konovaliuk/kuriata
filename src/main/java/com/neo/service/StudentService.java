package com.neo.service;

import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.dao.idao.ICompleteTestsDAO;
import com.neo.dao.idao.IStudentsDAO;
import com.neo.exceptions.DAOException;
import com.neo.exceptions.ServiceException;
import com.neo.service.iservice.IStudentService;

import java.util.List;

public class StudentService implements IStudentService{
    private IStudentsDAO iStudentsDAO;
    private ICompleteTestsDAO iCompleteTestsDAO;

    public StudentService(IStudentsDAO iStudentsDAO, ICompleteTestsDAO iCompleteTestsDAO) {
        this.iStudentsDAO = iStudentsDAO;
        this.iCompleteTestsDAO = iCompleteTestsDAO;
    }

    @Override
    public List<Student> getAllStudents() throws ServiceException {
        try{
            return iStudentsDAO.findAll();
        } catch (DAOException e){
            throw new ServiceException("Cant extract all students from DB.", e);
        }
    }

    //ToDo: uncomment and realize method below
//    @Override
//    public List<Student> getAllStudentsPaginated(int pageNumber, int perPage) {
//
//    }

    @Override
    public Student getStudentByID(int id) throws ServiceException {
        try{
            return iStudentsDAO.findById(id);
        } catch (DAOException e){
            throw new ServiceException("Cant extract student with id=" + id + " from DB.", e);
        }
    }

    @Override
    public Student loginStudent(String email) throws ServiceException {
        try{
            return iStudentsDAO.login(email);
        } catch (DAOException e){
            throw new ServiceException("Cant login student with email=" + email + " from DB.", e);
        }
    }

    @Override
    public List<CompleteTest> getStudentCompletedTests(Student student) throws ServiceException {
        try{
            return iCompleteTestsDAO.findAllCompletedTestsByStudent(student);
        } catch (DAOException e){
            throw new ServiceException("Cant extract all completed tests by student=" + student + " from DB.", e);
        }
    }

    @Override
    public int addStudent(Student student) throws ServiceException {
        try{
            return iStudentsDAO.create(student);
        }catch (DAOException e){
            throw new ServiceException("Cant write student=" + student + " to DB.", e);
        }
    }

    @Override
    public boolean updateStudent(Student student) throws ServiceException {
        try{
            return iStudentsDAO.update(student);
        }catch (DAOException e){
            throw new ServiceException("Cant update student=" + student + " in DB.", e);
        }
    }

    @Override
    public boolean deleteStudent(Student student) throws ServiceException {
        try{
            return iStudentsDAO.deleteById(student.getId());
        }catch (DAOException e){
            throw new ServiceException("Cant delete student=" + student + " from DB.", e);
        }
    }
}
