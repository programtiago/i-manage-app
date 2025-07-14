package com.devtiago.i_manage_app.backend.repository;

import com.devtiago.i_manage_app.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
