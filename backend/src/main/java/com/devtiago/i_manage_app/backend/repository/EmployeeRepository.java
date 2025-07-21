package com.devtiago.i_manage_app.backend.repository;

import com.devtiago.i_manage_app.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    boolean existsByWorkerNo(String workerNo);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}
