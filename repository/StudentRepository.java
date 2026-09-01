package com.example.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Özel sorgu türetme (Derived Query):
    // Spring Data JPA bu metot adını okur ve arkada otomatik olarak
    // "SELECT * FROM students WHERE student_number = ?" SQL sorgusunu üretir.
    Optional<Student> findByStudentNumber(String studentNumber);
}