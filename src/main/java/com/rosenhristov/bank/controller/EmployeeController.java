package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.entity.EmployeeEntity;
import com.rosenhristov.bank.exception.BankException;
import com.rosenhristov.bank.exception.ErrorResponse;
import com.rosenhristov.bank.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@Api(value = "Operations related to employees", tags = {"Employees"})
@SwaggerDefinition(tags = {@Tag(name = "Employees", description = "Operations related to employees")})
public class EmployeeController {

    private final EmployeeService service;

    @ApiOperation(value = "Retrieve all employees", notes = "Used to fetch all employees from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<Employee>> getAll() {
        log.info("GETting all employees");
        return ResponseEntity.of(Optional.of(service.getAll()));
    }


    @ApiOperation(value="Fetch an employee by id", notes = "Provide and id to lookup specific employee from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{employeeId}", produces = {"application/json"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        log.info("GETting employee with id {}", employeeId);
        Optional<Employee> result = service.getEmployeeById(employeeId);
        if (result.isEmpty()) {
            throw new BankException("Could not find employee with id: " + employeeId);
        }
        return ResponseEntity.of(result);
    }


    @ApiOperation(value="Add a new employee", notes = "Used to insert new employee in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeEntity.class),
            @ApiResponse(code = 201, message = "Created", response = EmployeeEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee newEmployee) {
        log.info("INSERTing employee {} {}",
                newEmployee.getName(),
                newEmployee.getSurname());
        Optional<Employee> result = Optional.ofNullable(
                service.save(
                        service.getMapper().toEntity(newEmployee)));
        if (result.isEmpty()) {
            throw new BankException(String.format(
                    "Could not save employee %s %s", newEmployee.getName(), newEmployee.getSurname()));
        }
        return ResponseEntity.of(
                Optional.ofNullable(service.save(
                        service.getMapper().toEntity(newEmployee))));
    }


    @ApiOperation(value="Update an employee", notes = "Used to update an employee with certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeEntity.class),
            @ApiResponse(code = 201, message = "Created", response = EmployeeEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PutMapping(value = "/{employeeId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long employeeId) {
        log.info("UPDATE-ing employee with id: {}", employeeId);
        Optional<Employee> result = service.getEmployeeById(employeeId);
        if (result.isEmpty()) {
            throw new BankException("Could not find employee with id: " + employeeId);
        }
        return ResponseEntity.of(
                result.map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setMidName(newEmployee.getMidName());
                    employee.setSurname(newEmployee.getSurname());
                    employee.setPosition(newEmployee.getPosition());
                    employee.setSalary(newEmployee.getSalary());
                    employee.setPhone(newEmployee.getPhone());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setDateHired(newEmployee.getDateHired());
                    employee.setStartOfExperience(newEmployee.getStartOfExperience());
                    employee.setClients(newEmployee.getClients());
                    employee.setAccounts(newEmployee.getAccounts());
                    Optional<Employee> dto = Optional.ofNullable(
                            service.save(
                                    service.getMapper().toEntity(employee)));
                    if (dto.isEmpty()) {
                        throw new BankException("Could not update employee with id: " + employeeId);
                    }
                    return dto.get();
                }));
    }


    @ApiOperation(value="Delete an employee with indicated id", notes = "Used to delete an employee by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @DeleteMapping(value = "/{employeeId}", produces = {"application/json"})
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        log.info("DELETE-ing bank account {}", employeeId);
        Optional<Employee> result = service.deleteEmployee(employeeId);
        if (result.isEmpty()) {
            throw new BankException("Could not delete employee with id: " + employeeId);
        }
        return ResponseEntity.of(result);
    }
}
