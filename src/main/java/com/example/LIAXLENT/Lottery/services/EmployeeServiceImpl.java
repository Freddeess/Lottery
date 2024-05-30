package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new RuntimeException("Anställd med id " + id +  "hittades inte");
        }
        return employee.get();
    }
    @Override
    public Employee save (Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public void deleteById(int id){
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Anställd med id " + id + " hittades inte");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    @Override
    public boolean verifyLogin(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null){
            throw new RuntimeException("Användare med email " + email + " hittades inte");
        }
        if(!employee.getPassword().equals(password)){
            throw new RuntimeException("Felaktigt lösenord!");
        }
        return true;
    }
    @Override
    public void registerUser(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new RuntimeException("Användare med email " + email + " hittades inte");
        }
        if (employee.getPassword() == null){
            employee.setPassword(password);
        } else{
            throw new RuntimeException("Användaren är redan registrerad");
        }

        employeeRepository.save(employee);
    }

}
