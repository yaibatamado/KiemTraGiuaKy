package com.midterm.kiemtra_giua_ky.repository;

import com.midterm.kiemtra_giua_ky.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);
}