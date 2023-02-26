package com.mano.api.controller;


import com.mano.api.model.Student;
import com.mano.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student registerNewStudent(@RequestBody Student student){

        Student savedStudent = studentService.addNewStudent(student);

        return savedStudent;
    }

    @DeleteMapping(path= "{studentId}")
    public void deleteStudent(@PathVariable("studenId") Long id ){
        studentService.deleteStudent(id);
    }

    @PutMapping(path= "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)String email){

        studentService.updateStudent(studentId,name,email);

    }



}
