package com.example.studentapp.service;

import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Dependency Injection (Bağımlılık Enjeksiyonu)
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Tüm öğrencileri getirme
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Yeni öğrenci kaydetme (İş Kuralı Kontrollü)
    public Student saveStudent(Student student) {
        // İş Kuralı: Aynı öğrenci numarasına sahip başka bir öğrenci var mı?
        studentRepository.findByStudentNumber(student.getStudentNumber())
                .ifPresent(existing -> {
                    throw new RuntimeException("Bu öğrenci numarası zaten kayıtlı: " + student.getStudentNumber());
                });

        return studentRepository.save(student);
    }

    // 3. ID ile öğrenci getirme
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı. ID: " + id));
    }

    // 4. Öğrenci silme
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Silinecek öğrenci bulunamadı. ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}