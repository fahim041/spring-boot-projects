package com.dev.studentmanagement__mvcthymeleaf;

import com.dev.studentmanagement__mvcthymeleaf.entity.Student;
import com.dev.studentmanagement__mvcthymeleaf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementMvcThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementMvcThymeleafApplication.class, args);
    }
}
