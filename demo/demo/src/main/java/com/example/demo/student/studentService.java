package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@Service beans hold the business logic
// and call methods in the repository
// layer.
public class studentService {

    private final StudentRepository studentRepository;

    @Autowired
    public studentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<student> getStudents(){
//        static way
//		return - an array of objects
//        return List.of(
//                new student(
//                        1L,
//                        "muhammad aman",
//                        "nadeemaman608@gmail.com",
//                        LocalDate.of(2001, Month.OCTOBER, 5 ),
//                        21
//                )
//        );

//        dynamic way

//        this is magic of spring jpa
        return studentRepository.findAll();
    }

    public void addNewStudent(student stud) {
//        from service - writing business logic

         Optional<student> studentOptional = studentRepository.findStudentByEmail(stud.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
//        adding to a database
        studentRepository.save(stud);
         System.out.println(stud);

    }

    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with " + id + "does not exist");

        }
        studentRepository.deleteById(id);


    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        student stud = studentRepository.findById(studentId).orElseThrow(() ->  new IllegalStateException(
                "Student with id " + studentId + " does not exist"
        ));

        if(name != null &&
            name.length() >  0 &&
            !Objects.equals(stud.getName(), name)){
            stud.setName(name);
        }
        if(email != null &&
            email.length() > 0 &&
            !Objects.equals(stud.getEmail(), email))
        {
          Optional<student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            stud.setEmail(email);
        }




    }
}
