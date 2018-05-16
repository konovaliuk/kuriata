package com.neo.beans;

public class Student extends Person {
    private String firstName;
    private String lastName;
    private String email;
    private double rating;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, double rating) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [" +
                "id='" + getId() + '\'' +
                ", firs name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", rating='" + rating + '\'' +
                "]\n";
    }
}
