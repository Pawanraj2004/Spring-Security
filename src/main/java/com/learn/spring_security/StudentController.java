package com.learn.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students= new ArrayList<>(List.of(
                    new Student(1,"Pawan",100),
                    new Student(2,"Raj",90)));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return this.students;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        this.students.add(student);
        return student;
    }

}
