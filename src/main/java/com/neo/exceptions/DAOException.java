package com.neo.exceptions;

import java.sql.SQLException;

public class DAOException extends SQLException{
    public DAOException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
