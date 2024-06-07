package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Account;
import com.example.LIAXLENT.Lottery.entities.Employee;
import com.example.LIAXLENT.Lottery.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountService accountService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AccountService accountService) {
        this.employeeRepository = employeeRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new RuntimeException("Anställd med id " + id + " hittades inte");
        }
        return employee.get();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
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
        if (employee == null) {
            throw new RuntimeException("Användare med email " + email + " hittades inte");
        }
        if (!employee.getPassword().equals(password)) {
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
        System.out.println("Hittade anställd med email: " + email);

        if (employee.getPassword() == null) {
            employee.setPassword(password);
            employeeRepository.save(employee);
            System.out.println("Uppdaterade lösenord för anställd med ID: " + employee.getId());

            Account existingAccount = accountService.findAccountByEmployeeId(employee.getId());
            if (existingAccount != null) {
                System.out.println("Ett konto finns redan för denna anställd med ID: " + employee.getId());
                return;
            }
            System.out.println("Inget konto hittades, skapar nytt konto för anställd med ID: " + employee.getId());

            Account newAccount = new Account();
            newAccount.setEmployee(employee);
            newAccount.setBalance(0);

            System.out.println("Sparar nytt konto för anställd med ID: " + employee.getId());
            accountService.save(newAccount);

            System.out.println("Registrering slutförd för email: " + email);
        } else {
            throw new RuntimeException("Användaren är redan registrerad");
        }
    }
}