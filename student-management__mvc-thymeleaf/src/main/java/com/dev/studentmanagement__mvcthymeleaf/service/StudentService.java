package com.dev.studentmanagement__mvcthymeleaf.service;

import com.dev.studentmanagement__mvcthymeleaf.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);

    void deleteStudent(Long id);
}
