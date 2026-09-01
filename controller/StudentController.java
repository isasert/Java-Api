package com.example.studentapp.controller;

import com.example.studentapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Dependency Injection: Service katmanını enjekte ediyoruz
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. TÜM ÖĞRENCİLERİ GETİR (GET -> http://localhost:8080/api/students)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 2. YENİ ÖĞRENCİ EKLE (POST -> http://localhost:8080/api/students)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // 3. TEK BİR ÖĞRENCİ GETİR (GET -> http://localhost:8080/api/students/1)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // 4. ÖĞRENCİ SİL (DELETE -> http://localhost:8080/api/students/1)
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return id + " ID'li öğrenci başarıyla silindi.";
    }
}