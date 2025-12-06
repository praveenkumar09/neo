package com.praveen.neo.controller;

import com.praveen.neo.entity.Student;
import com.praveen.neo.model.CreateStudentRequest;
import com.praveen.neo.model.UpdateStudentRequest;
import com.praveen.neo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createStudent(
            @RequestBody CreateStudentRequest createStudentRequest
    ){
        return studentService
                .createStudent(createStudentRequest);
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(
            @PathVariable Long id
    ){
        return studentService
                .getStudentById(id);
    }

    @GetMapping("/getStudentByName/{name}")
    public List<Student> getStudentByName(
            @PathVariable String name
    ){
        return studentService
                .getStudentByName(name);
    }

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(){
        return studentService
                .findAllStudent();
    }

    @PutMapping("/update")
    public Student updateStudent(
            @RequestBody UpdateStudentRequest updateStudentRequest
    ){
        return studentService
                .updateStudent(updateStudentRequest);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(
            @PathVariable Long id
    ){
         return studentService
                .deleteStudent(id);
    }

    @GetMapping("/getStudentByNameAndBirthYear/{name}/{birthYear}")
    public List<Student> getStudentByNameAndBirthYear(
            @PathVariable String name,
            @PathVariable Integer birthYear
    ){
        return studentService
                .getStudentByNameAndBirthYear(name,birthYear);
    }
}
