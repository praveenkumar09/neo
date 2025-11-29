package com.praveen.neo.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class IsLearningRelationship {

    public IsLearningRelationship() {
    }

    public IsLearningRelationship(Long marks, Subject subject) {
        this.marks = marks;
        this.subject = subject;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long marks;

    @TargetNode
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
