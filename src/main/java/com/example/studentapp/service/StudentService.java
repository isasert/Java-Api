package com.example.studentapp.service;

import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        // Kontrol: Aynı öğrenci numarası var mı?
        studentRepository.findByStudentNumber(student.getStudentNumber())
                .ifPresent(existing -> {
                    throw new RuntimeException("Bu öğrenci numarası zaten kayıtlı: " + student.getStudentNumber());
                });

        return studentRepository.save(student);
    }

    public Student getStudentById(Long id ) {
        id++;
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı. ID: " + id));
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Silinecek öğrenci bulunamadı. ID: " + id);
        }
        studentRepository.deleteById(id);
    }


}