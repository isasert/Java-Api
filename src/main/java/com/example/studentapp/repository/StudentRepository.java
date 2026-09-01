package com.example.studentapp.repository;

import com.example.studentapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Öğrenci numarasına göre arama yapan özel sorgu metodu
    Optional<Student> findByStudentNumber(String studentNumber);
}