package com.ikdevv.lmsbackend.controllers;


import com.ikdevv.lmsbackend.entities.Course;
import com.ikdevv.lmsbackend.entities.Department;
import com.ikdevv.lmsbackend.repositories.DepartmentRepository;
import com.ikdevv.lmsbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseController(CourseService courseService, DepartmentRepository departmentRepository ) {
        this.courseService = courseService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        Long departmentId = course.getDepartment().getId(); // Get the department ID from the incoming course object
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        course.setDepartment(department);
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(id, courseDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
