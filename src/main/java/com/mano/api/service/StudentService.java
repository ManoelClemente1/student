package com.mano.api.service;

import com.mano.api.model.Student;
import com.mano.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if(!studentExists){
            throw new IllegalArgumentException("Student with id: " + id
                        + " does not exists!");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id: " + studentId
                        + " does not exists!"));

        if(!name.isBlank()){
            student.setName(name);
        }

        if(!email.isBlank()){
            student.setEmail(email);
        }

        studentRepository.save(student);

    }
}
