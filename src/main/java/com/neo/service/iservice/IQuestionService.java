package com.neo.service.iservice;

import com.neo.beans.Question;
import com.neo.exceptions.ServiceException;

import java.util.List;

public interface IQuestionService extends IService{
    List<Question> getAllQuestions() throws ServiceException;

    /**
     * return generated id
     * @param question
     * @return
     */
    int addQuestion(Question question) throws ServiceException;
    boolean updateQuestion(Question question) throws ServiceException;
    boolean deleteQuestionById(Question question) throws ServiceException;

}
