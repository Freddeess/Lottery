package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

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
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = null;
        if (emp.isPresent()){
            employee = emp.get();
        }
        else {
            throw new RuntimeException("Anställd med id "+id+" hittades inte");
        }
        return employee;
    }

    @Override
    public Employee save (Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }

}
