package com.neo.beans;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable, Cloneable {
    private int id;
    public BaseEntity(){}

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        BaseEntity entity = (BaseEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
