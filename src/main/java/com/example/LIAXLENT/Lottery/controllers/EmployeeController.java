package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/anställda")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }
    @GetMapping("/anställda/{id}")
    public Employee findEmployee(@PathVariable int id) {
        return employeeService.findById(id);
    }
    @PostMapping("/anställda")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }




}
