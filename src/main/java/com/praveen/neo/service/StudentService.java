package com.praveen.neo.service;

import com.praveen.neo.entity.Department;
import com.praveen.neo.entity.IsLearningRelationship;
import com.praveen.neo.entity.Student;
import com.praveen.neo.entity.Subject;
import com.praveen.neo.model.CreateStudentRequest;
import com.praveen.neo.model.CreateSubjectRequest;
import com.praveen.neo.model.UpdateStudentRequest;
import com.praveen.neo.repository.DepartmentRepository;
import com.praveen.neo.repository.StudentRepository;
import com.praveen.neo.repository.SubjectRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.departmentRepository = departmentRepository;
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Department department = new Department();
        department.setDepName(createStudentRequest.getDepartment().getDepName());
        department = departmentRepository.save(department);

        List<IsLearningRelationship> isLearningRelationshipList = new ArrayList<>();
        if(createStudentRequest.getSubjectList() != null){
            for(CreateSubjectRequest createSubjectRequest :
                    createStudentRequest.getSubjectList()){
                Subject subject = new Subject();
                subject.setSubName(createSubjectRequest.getSubjectName());
                Subject savedSubject = subjectRepository.save(subject);
                IsLearningRelationship isLearningRelationship = new IsLearningRelationship(createSubjectRequest.getMarks(), savedSubject);
                isLearningRelationshipList.add(isLearningRelationship);
            }
        }

        Student student = new Student();
        student.setBirthYear(createStudentRequest.getBirthYear());
        student.setName(createStudentRequest.getName());
        student.setCountry(createStudentRequest.getCountry());
        student.setDepartment(department);
        student.setIsLearningRelationshipList(isLearningRelationshipList);
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository
                .findByName(name);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        return studentRepository
                .findById(updateStudentRequest.getId())
                .map(student -> {
                    student.setName(updateStudentRequest.getName());
                    student.setBirthYear(updateStudentRequest.getBirthYear());
                    student.setCountry(updateStudentRequest.getCountry());
                    return studentRepository.save(student);
                }).orElseThrow();
    }
}
