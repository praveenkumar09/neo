package com.praveen.neo.repository;

import com.praveen.neo.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student,Long> {
    List<Student> findByName(String name);
}
