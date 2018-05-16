package com.neo.dao.idao;

import com.neo.beans.Student;
import com.neo.dao.mysqldao.RatingComparingConstant;
import com.neo.exceptions.DAOException;

import java.util.List;

public interface IStudentsDAO extends IDAO<Student> {
    List<Student> findByRating(RatingComparingConstant ratingComparingConstant, double... rating) throws DAOException;

    List<Student> findByLastName(String lastName) throws DAOException;

    Student login(String studentEmail) throws DAOException;
}
