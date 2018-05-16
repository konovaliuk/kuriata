package com.neo.beans;

public class Admin extends Person {
    private String name;
    private String password;

    public Admin() {
    }

    public Admin(int id, String name, String password) {
        this.setId(id);
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [" +
                "id='"+ getId() + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                "]\n";
    }
}
