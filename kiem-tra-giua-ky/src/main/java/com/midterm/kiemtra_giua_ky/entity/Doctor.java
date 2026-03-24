package com.midterm.kiemtra_giua_ky.entity;

import jakarta.persistence.*;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;
    private String image;

    @ManyToOne
    @JoinColumn(name = "department_id") // 🔥 QUAN TRỌNG
    private Department department;

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getImage() {
        return image;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}