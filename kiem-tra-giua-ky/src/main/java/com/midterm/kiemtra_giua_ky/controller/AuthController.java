package com.midterm.kiemtra_giua_ky.controller;

import com.midterm.kiemtra_giua_ky.entity.Patient;
import com.midterm.kiemtra_giua_ky.repository.PatientRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private PatientRepository patientRepo;

    // 👉 Vào web là về login
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // ================= REGISTER =================
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Patient patient) {

        // 🔥 gán role mặc định
        patient.setRole("PATIENT");

        patientRepo.save(patient);

        return "redirect:/login";
    }

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Patient p = patientRepo.findByUsername(username);

        if (p != null && p.getPassword().equals(password)) {

            // 🔥 lưu session
            session.setAttribute("user", p);

            return "redirect:/home";
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        return "login";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}