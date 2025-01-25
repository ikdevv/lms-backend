package com.ikdevv.lmsbackend.repositories;


import com.ikdevv.lmsbackend.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
