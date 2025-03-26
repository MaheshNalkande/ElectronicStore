package com.project.ecom.repository;

import com.project.ecom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    List<User> findByNameContaining(String keyword);
}
