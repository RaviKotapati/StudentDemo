package com.demo.studentdemo.service;

import com.demo.studentdemo.model.Student;
import com.demo.studentdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        Optional<Student> optionalStudent = repository.findById(id);
        return optionalStudent.isPresent() ? optionalStudent: Optional.empty();

    }

    public Student addStudent(Student student){
        return repository.save(student);
    }

    public Optional<Student> updateStudent(Student student){
        Optional<Student> optionalStudent = repository.findById(student.getId());
        if(optionalStudent.isPresent()){
            Student s = optionalStudent.get();
            s.setStudentName(student.getStudentName());
            s.setAddress(student.getAddress());
            s.setGrade(student.getGrade());
            return Optional.of(repository.save(s));
        }
        return Optional.empty();
    }

    public boolean deleteStudent(Long studentId){
        Optional<Student> optionalStudent = repository.findById(studentId);
        if(optionalStudent.isPresent()){
            repository.delete(optionalStudent.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
