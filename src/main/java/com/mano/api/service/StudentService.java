package com.mano.api.service;

import com.mano.api.model.Student;
import com.mano.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository
                .findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalArgumentException("email taken");
        }

        studentRepository.save(student);
    }
}
