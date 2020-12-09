package com.dataart.bank.repository;

import com.dataart.bank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    default Optional<Employee> removeEmployeeById(Long id) {
        Optional<Employee> employee = findById(id);
        if (employee.isPresent()) {
            delete(employee.get());
            return employee;
        }
        return Optional.empty();
    }
}
