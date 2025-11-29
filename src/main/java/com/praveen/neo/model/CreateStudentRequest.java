package com.praveen.neo.model;

import java.util.List;

public class CreateStudentRequest {

    private String name;
    private String country;
    private Integer birthYear;
    private List<CreateSubjectRequest> subjectList;
    private CreateDepartmentRequest department;

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

    public List<CreateSubjectRequest> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<CreateSubjectRequest> subjectList) {
        this.subjectList = subjectList;
    }

    public CreateDepartmentRequest getDepartment() {
        return department;
    }

    public void setDepartment(CreateDepartmentRequest department) {
        this.department = department;
    }
}
