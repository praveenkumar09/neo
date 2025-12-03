package com.praveen.neo.controller;

import com.praveen.neo.entity.Student;
import com.praveen.neo.model.CreateStudentRequest;
import com.praveen.neo.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
