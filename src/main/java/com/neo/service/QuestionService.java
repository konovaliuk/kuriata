package com.neo.service;

import com.neo.beans.Question;
import com.neo.dao.idao.IQuestionsDAO;
import com.neo.exceptions.DAOException;
import com.neo.exceptions.ServiceException;
import com.neo.service.iservice.IQuestionService;

import java.util.List;

public class QuestionService implements IQuestionService {
    private IQuestionsDAO iQuestionsDAO;

    public QuestionService(IQuestionsDAO iQuestionsDAO) {
        this.iQuestionsDAO = iQuestionsDAO;
    }

    @Override
    public List<Question> getAllQuestions() throws ServiceException {
        try {
            return iQuestionsDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Cant extract all questions from DB.", e);
        }

    }

    @Override
    public int addQuestion(Question question) throws ServiceException {
        try {
            return iQuestionsDAO.create(question);
        } catch (DAOException e) {
            throw new ServiceException("Cant save question=" + question + " to DB.", e);
        }
    }

    @Override
    public boolean updateQuestion(Question question) throws ServiceException {
        try{
            return iQuestionsDAO.update(question);
        } catch (DAOException e){
            throw new ServiceException("Cant update question=" + question + " in DB.", e);
        }
    }

    @Override
    public boolean deleteQuestionById(Question question) throws ServiceException {
        try{
            return iQuestionsDAO.deleteById(question.getId());
        } catch (DAOException e){
            throw new ServiceException("Cant delete question=" + question + " from DB.", e);
        }
    }
}
