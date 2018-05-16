package com.neo.dao.mysqldao;

public enum RatingComparingConstant {
    RATING_EQUAL_TO(" = ?"),
    RATING_GREATER_THEN(" > ?"),
    RATING_LESS_THEN(" < ?"),
    RATING_BETWEEN(" BETWEEN ? AND ?");
    //LAST_NAME_LIKE("lastName LIKE ?");

    private final String SqlSubString;

    private RatingComparingConstant(String s){
        SqlSubString = s;
    }

    public String toString(){
        return this.SqlSubString;
    }
}
