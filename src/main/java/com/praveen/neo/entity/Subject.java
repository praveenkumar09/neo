package com.praveen.neo.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node(labels = "Subject")
public class Subject {

    public Subject() {
    }

    public Subject(Long id, String subName) {
        this.id = id;
        this.subName = subName;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Property("sub_name")
    private String subName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subName='" + subName + '\'' +
                '}';
    }
}
