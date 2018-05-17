package com.neo.dao.idao;

import com.neo.beans.BaseEntity;
import com.neo.exceptions.DAOException;
import com.neo.exceptions.ServiceException;

import java.util.List;

public interface IDAO<T extends BaseEntity> {
    List<T> findAll() throws DAOException;

    T findById(int id) throws DAOException;

    int create(T entity) throws DAOException, ServiceException;

    boolean update(T entity) throws DAOException;

    boolean deleteById(int id) throws DAOException;
}
