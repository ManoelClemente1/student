package com.mano.api.controller;


import com.mano.api.model.Student;
import com.mano.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path= "{studentId}")
    public void deleteStudent(@PathVariable("studenId") Long id ){
        studentService.deleteStudent(id);
    }

}
