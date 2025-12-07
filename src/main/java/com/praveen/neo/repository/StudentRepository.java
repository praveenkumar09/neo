package com.praveen.neo.repository;

import com.praveen.neo.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student,Long> {
    List<Student> findByName(String name);
    List<Student> findByNameAndBirthYear(String name,Integer year);
    List<Student> findByNameOrBirthYear(String name,Integer year);
    List<Student> findByBirthYearIn(List<Integer> birthYear);

    @Query("MATCH (s:Student) WHERE s.name = $name " +
            "and s.birth_year = $year RETURN s")
    List<Student> getByNameAndBirthYear(String name,Integer year);

    @Query("MATCH (n:Student)-[rel]->(node) " +
            "where n.name= $name " +
            "and n.birth_year= $year " +
            "RETURN n,rel,node")
    List<Student> getByNameBirthYearAndLoadRelationships(String name,Integer year);
}
