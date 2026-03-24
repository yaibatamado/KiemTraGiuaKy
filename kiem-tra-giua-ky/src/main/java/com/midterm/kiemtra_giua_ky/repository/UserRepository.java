package com.midterm.kiemtra_giua_ky.repository;

import com.midterm.kiemtra_giua_ky.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}