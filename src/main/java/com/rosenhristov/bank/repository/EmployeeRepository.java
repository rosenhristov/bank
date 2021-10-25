package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    default Optional<EmployeeEntity> removeEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = findById(id);
        if (employee.isPresent()) {
            delete(employee.get());
            return employee;
        }
        return Optional.empty();
    }
}
