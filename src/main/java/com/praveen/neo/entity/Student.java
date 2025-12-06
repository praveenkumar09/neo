package com.praveen.neo.entity;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node(labels = "Student")
public class Student {

    public Student() {
    }

    public Student(String name, String country, Integer birthYear, Department department, List<IsLearningRelationship> isLearningRelationshipList) {
        this.name = name;
        this.country = country;
        this.birthYear = birthYear;
        this.department = department;
        this.isLearningRelationshipList = isLearningRelationshipList;
    }

    @Id
    @GeneratedValue
    private long id;

    @Property("name")
    private String name;

    @Property("country")
    private String country;

    @Property("birth_year")
    private Integer birthYear;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Department department;

    @Relationship(type = "IS_LEARNING", direction = Relationship.Direction.OUTGOING)
    private List<IsLearningRelationship> isLearningRelationshipList;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<IsLearningRelationship> getIsLearningRelationshipList() {
        return isLearningRelationshipList;
    }

    public void setIsLearningRelationshipList(List<IsLearningRelationship> isLearningRelationshipList) {
        this.isLearningRelationshipList = isLearningRelationshipList;
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
