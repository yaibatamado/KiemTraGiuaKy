package com.midterm.kiemtra_giua_ky.repository;

import com.midterm.kiemtra_giua_ky.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}