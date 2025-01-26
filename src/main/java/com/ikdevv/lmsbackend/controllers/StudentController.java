package com.ikdevv.lmsbackend.controllers;



import com.ikdevv.lmsbackend.entities.Course;
import com.ikdevv.lmsbackend.repositories.CourseRepository;
import com.ikdevv.lmsbackend.services.StudentService;
import com.ikdevv.lmsbackend.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public Student createCourse(@RequestBody Student student) {
        Long courseId = student.getCourse().getId(); // Get the department ID from the incoming course object
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        student.setCourse(student.getCourse());
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
