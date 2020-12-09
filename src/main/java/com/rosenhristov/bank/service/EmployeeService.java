package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.mapper.EmployeeMapper;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        return opt.map(employee -> mapper.toDto(employee));
    }

    public List<EmployeeDTO> getAll() {
        return mapper.toDtos(employeeRepository.findAll());
    }

    public EmployeeDTO save(Employee employee) {
        return mapper.toDto(employeeRepository.save(employee));
    }

    public List<EmployeeDTO> saveAll(List<Employee> employee) {
        return mapper.toDtos(employeeRepository.saveAll(employee));
    }

    public Optional<EmployeeDTO> deleteEmployee(Long id) {
        Optional<Employee> opt = employeeRepository.removeEmployeeById(id);
        return opt.map(entity -> mapper.toDto(entity));
    }

    public EmployeeMapper getMapper() {
        return mapper;
    }
}
