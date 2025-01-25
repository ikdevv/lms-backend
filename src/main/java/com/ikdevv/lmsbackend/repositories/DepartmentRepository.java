package com.ikdevv.lmsbackend.repositories;

import com.ikdevv.lmsbackend.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
