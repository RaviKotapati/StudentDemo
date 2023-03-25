package com.demo.studentdemo.controller;


import com.demo.studentdemo.model.Student;
import com.demo.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


    /**
     *
     * @return
     */
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable(value = "id") Long studentId){
        return ResponseEntity.ok().body(studentService.getStudentById(studentId));

    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> updateStudent(@PathVariable(value = "id") Long studentId, @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId){
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", studentService.deleteStudent(studentId));
        return response;
    }
}
