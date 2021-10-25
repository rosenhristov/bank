package com.rosenhristov.bank.service;

import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.entity.EmployeeEntity;
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

    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Retrieving employee with id: {} from database", id);
        return employeeRepository
                .findById(id)
                .map(employee -> mapper.toDto(employee));
    }

    public List<Employee> getAll() {
        log.info("Retrieving all the employees from the database.");
        return mapper.toDtos((List<EmployeeEntity>) employeeRepository.findAll());
    }

    public Employee save(EmployeeEntity employeeEntity) {
        log.info("Saving employee {} {} to database", employeeEntity.getName(), employeeEntity.getSurname());
        return mapper.toDto(employeeRepository.save(employeeEntity));
    }

    public List<Employee> saveAll(List<EmployeeEntity> employeeEntity) {
        log.info("Saving all employees to database.");
        return mapper.toDtos((List<EmployeeEntity>) employeeRepository.saveAll(employeeEntity));
    }

    public Optional<Employee> deleteEmployee(Long id) {
        log.info("Deleting employee with id: {} from database", id);
        Optional<EmployeeEntity> optEmployee = employeeRepository.removeEmployeeById(id);
        if (optEmployee.isPresent()) {
            return optEmployee.map(entity -> mapper.toDto(entity));
        }
        return Optional.empty();
    }

    public EmployeeMapper getMapper() {
        return mapper;
    }
}
