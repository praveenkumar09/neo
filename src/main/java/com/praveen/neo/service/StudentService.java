package com.praveen.neo.service;

import com.praveen.neo.entity.Department;
import com.praveen.neo.entity.IsLearningRelationship;
import com.praveen.neo.entity.Student;
import com.praveen.neo.entity.Subject;
import com.praveen.neo.model.CreateStudentRequest;
import com.praveen.neo.model.CreateSubjectRequest;
import com.praveen.neo.model.GetStudentsByBirthYear;
import com.praveen.neo.model.UpdateStudentRequest;
import com.praveen.neo.repository.DepartmentRepository;
import com.praveen.neo.repository.StudentRepository;
import com.praveen.neo.repository.SubjectRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

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
        String depName = createStudentRequest.getDepartment().getDepName();
        Department department = createDepartment().apply(depName);

        List<IsLearningRelationship> isLearningRelationshipList = new ArrayList<>();
        if(createStudentRequest.getSubjectList() != null){
            processSubjectList(isLearningRelationshipList, createStudentRequest.getSubjectList());
        }

        return createStudentAndReturn(createStudentRequest,department,isLearningRelationshipList);
    }

    private Student createStudentAndReturn(
            CreateStudentRequest createStudentRequest,
            Department department,
            List<IsLearningRelationship> isLearningRelationshipList
    ) {
        Student student = new Student(
                createStudentRequest.getName(),
                createStudentRequest.getCountry(),
                createStudentRequest.getBirthYear(),
                department,
                isLearningRelationshipList
        );
        return studentRepository.save(student);
    }

    private void processSubjectList(List<IsLearningRelationship> isLearningRelationshipList, List<CreateSubjectRequest> subjectList) {
        subjectList
                .parallelStream()
                .filter(subject -> subject.getSubjectName() != null && !subject.getSubjectName().isBlank())
                .forEach(subject -> {
                    Subject savedSubject = createSubject().apply(subject.getSubjectName());
                    createRelationshipFromSubject(savedSubject).accept(subject.getMarks(), isLearningRelationshipList);
                });
    }

    public BiConsumer<Long,List<IsLearningRelationship>> createRelationshipFromSubject(Subject subject){
        return (marks,isLearningRelationshipList) -> {
            IsLearningRelationship isLearningRelationship = new IsLearningRelationship(marks,subject);
            isLearningRelationshipList.add(isLearningRelationship);
        };
    }

    public Function<String,Subject> createSubject(){
        return (subjectName) -> {
            Subject bySubjectName = subjectRepository.findBySubName(subjectName);
            if(bySubjectName != null){
                return bySubjectName;
            }else {
                Subject subject = new Subject();
                subject.setSubName(subjectName);
                return subjectRepository.save(subject);
            }
        };
    }

    public Function<String,Department> createDepartment(){
        return (depName) -> {
            Department byDepName = departmentRepository.findByDepName(depName);
            if(byDepName != null){
                return byDepName;
            }else{
                Department department = new Department();
                department.setDepName(depName);
                return departmentRepository.save(department);
            }
        };
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

    public String deleteStudent(Long id) {
         studentRepository
                .deleteById(id);
         return "Student deleted successfully";
    }

    public List<Student> getStudentByNameAndBirthYear(String name, Integer birthYear) {
        return studentRepository.getByNameBirthYearAndLoadRelationships(name,birthYear);
    }

    public List<Student> getStudentByNameOrBirthYear(String name, Integer birthYear) {
        return studentRepository.findByNameOrBirthYear(name,birthYear);
    }

    public List<Student> getStudentsByBirthYear(GetStudentsByBirthYear getStudentsByBirthYear) {
        return studentRepository
                .findByBirthYearIn(getStudentsByBirthYear.getBirthYear());
    }
}
