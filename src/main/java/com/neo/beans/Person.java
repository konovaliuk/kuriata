package com.neo.beans;

public class Person extends BaseEntity{
    private ROLE role;

    public enum  ROLE{
        ADMIN, STUDENT
    }
}
