package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.exception.mapper.EmployeeMapper;
import com.rosenhristov.bank.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Retrieving employee with id = {} from database", id);
        return employeeRepository
                .findById(id)
                .map(employee -> mapper.toDto(employee));
    }

    public List<EmployeeDTO> getAll() {
        log.info("Retrieving all the employees from the database.");
        return mapper.toDtos(employeeRepository.findAll());
    }

    public EmployeeDTO save(Employee employee) {
        log.info("Saving employee {} {} to database",
                employee.getName(),
                employee.getSurname());
        return mapper.toDto(employeeRepository.save(employee));
    }

    public List<EmployeeDTO> saveAll(List<Employee> employee) {
        log.info("Saving all employees to database.");
        return mapper.toDtos(employeeRepository.saveAll(employee));
    }

    public Optional<EmployeeDTO> deleteEmployee(Long id) {
        log.info("Deleting employee with id = {} from database", id);
        Optional<Employee> opt = employeeRepository.removeEmployeeById(id);
        return opt.map(entity -> mapper.toDto(entity));
    }

    public EmployeeMapper getMapper() {
        return mapper;
    }
}
