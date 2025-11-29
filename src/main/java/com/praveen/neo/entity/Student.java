package com.praveen.neo.entity;

public class Student {

    public Student() {
    }

    public Student(long id, String name, String country, Integer birthYear) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.birthYear = birthYear;
    }

    private long id;

    private String name;

    private String country;

    private Integer birthYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
