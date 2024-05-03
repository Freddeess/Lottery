package com.example.LIAXLENT.Lottery.repositories;

import com.example.LIAXLENT.Lottery.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
