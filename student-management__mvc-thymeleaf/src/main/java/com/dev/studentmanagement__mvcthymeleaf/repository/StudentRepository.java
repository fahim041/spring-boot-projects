package com.dev.studentmanagement__mvcthymeleaf.repository;

import com.dev.studentmanagement__mvcthymeleaf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
