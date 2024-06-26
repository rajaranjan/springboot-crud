package com.luv2code.cruddemo.rest;

import com.luv2code.cruddemo.pojo.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... only once
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();
        theStudents.add(new Student("Purnima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint /students - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define endpoint /students/{studentId} - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {


        // check the studentId aginst list size
        if((studentId >= theStudents.size()) || (studentId<0)) {
            throw new StudentNotFoundException("Student not found with id - " + studentId);
        }
        // get student with id
        return theStudents.get(studentId);
    }


}
