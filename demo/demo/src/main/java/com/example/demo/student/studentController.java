package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class studentController {

//    dependency injection implemented
    private final studentService studentSer;

    @Autowired
    public studentController(studentService studentSer)
    {
        this.studentSer = studentSer;
    }

    @GetMapping
    public List<student> getStudents(){

        return this.studentSer.getStudents();
    }

//    @request body is used it will take the request from client side
    @PostMapping
     public void createNewStudent(@RequestBody  student student){

        // from controller to service
        studentSer.addNewStudent(student);
     }

     @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long id){
        studentSer.deleteStudent(id);

     }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId")Long studentId,
                              @RequestParam (required = false) String name,
                              @RequestParam (required = false) String email){

        studentSer.updateStudent(studentId, name, email);
    }
    {

     }

}
