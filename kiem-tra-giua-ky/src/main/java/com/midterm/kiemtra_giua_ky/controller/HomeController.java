package com.midterm.kiemtra_giua_ky.controller;

import com.midterm.kiemtra_giua_ky.entity.Doctor;
import com.midterm.kiemtra_giua_ky.repository.DoctorRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private DoctorRepository repo;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       HttpSession session) {

        // 🔥 CHƯA LOGIN → ĐÁ RA LOGIN
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        Page<Doctor> doctors = repo.findAll(PageRequest.of(page, 5));
        model.addAttribute("doctors", doctors);

        return "home";
    }
}