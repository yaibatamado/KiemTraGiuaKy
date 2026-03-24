package com.midterm.kiemtra_giua_ky.controller;

import com.midterm.kiemtra_giua_ky.entity.Doctor;
import com.midterm.kiemtra_giua_ky.entity.Patient;
import com.midterm.kiemtra_giua_ky.repository.DoctorRepository;
import com.midterm.kiemtra_giua_ky.repository.DepartmentRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    // 🔥 CHECK ADMIN
    private boolean isAdmin(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) return false;

        Patient p = (Patient) user;
        return "ADMIN".equals(p.getRole());
    }

    // ================= ADD =================
    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentRepo.findAll());
        return "doctor-form";
    }

    // ================= SAVE =================
    @PostMapping("/save")
    public String save(@ModelAttribute Doctor doctor, HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        doctorRepo.save(doctor);
        return "redirect:/home";
    }

    // ================= DELETE =================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        doctorRepo.deleteById(id);
        return "redirect:/home";
    }

    // ================= EDIT =================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model,
                       HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        Doctor doctor = doctorRepo.findById(id).orElse(null);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentRepo.findAll());

        return "doctor-form";
    }
}