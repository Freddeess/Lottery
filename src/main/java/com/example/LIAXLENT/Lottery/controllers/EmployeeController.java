package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{id}")
    public Employee findEmployee(@PathVariable int id) {
        return employeeService.findById(id);
    }
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PatchMapping("/my-password")
    public ResponseEntity<?> updateMyPassword(HttpSession session,
                                              @RequestParam (value = "newPassword", required = true) String newPassword){
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseEntity<>("Ingen användare är inloggad.", HttpStatus.UNAUTHORIZED);
        }
        loggedInUser.setPassword(newPassword);
        employeeService.save(loggedInUser);
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteById(id);
    }

}