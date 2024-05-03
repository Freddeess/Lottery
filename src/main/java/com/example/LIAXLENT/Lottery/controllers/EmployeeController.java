package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    @GetMapping("/anställda")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/anställda/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.findById(id);
    }


}
