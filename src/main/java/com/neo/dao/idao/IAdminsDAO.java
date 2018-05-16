package com.neo.dao.idao;

import com.neo.beans.Admin;
import com.neo.exceptions.DAOException;

public interface IAdminsDAO extends IDAO<Admin> {
    Admin login(String adminUserName, String adminPassword) throws DAOException;
}
