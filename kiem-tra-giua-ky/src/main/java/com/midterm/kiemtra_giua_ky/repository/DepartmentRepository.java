package com.midterm.kiemtra_giua_ky.repository;

import com.midterm.kiemtra_giua_ky.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}