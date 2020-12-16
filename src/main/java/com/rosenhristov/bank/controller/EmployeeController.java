package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.exception.BankException;
import com.rosenhristov.bank.exception.ErrorResponse;
import com.rosenhristov.bank.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/", produces = {"application/json"})
    public CollectionModel<EntityModel<EmployeeDTO>> getAll() {
        log.info("GETting all employees");
        return (CollectionModel<EntityModel<EmployeeDTO>>)
                CollectionModel.of(
                        EntityModel.of(
                                service.getAll(),
                                List.of(Link.of("http://localhost:8080/bank/employees/employeeId"))));
    }


    @ApiOperation(value="Fetch an employee by id", notes = "Provide and id to lookup specific employee from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{employeeId}", produces = {"application/json"})
    public EntityModel<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        log.info("GETting employee with id {}", employeeId);
        Optional<EmployeeDTO> result = service.getEmployeeById(employeeId);
        if (result.isEmpty()) {
            throw new BankException("Could not find employee with id: " + employeeId);
        }
        return EntityModel.of(
                result.get(),
                List.of(Link.of("http://localhost:8080/bank/")));
    }


    @ApiOperation(value="Add a new employee", notes = "Used to insert new employee in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 201, message = "Created", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public EntityModel<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO newEmployee) {
        log.info("INSERTing employee {} {}",
                newEmployee.getName(),
                newEmployee.getSurname());
        Optional<EmployeeDTO> result = Optional.ofNullable(
                service.save(
                        service.getMapper().toEntity(newEmployee)));
        if (result.isEmpty()) {
            throw new BankException(String.format(
                    "Could not save employee %s %s", newEmployee.getName(), newEmployee.getSurname()));
        }
        return EntityModel.of(
                result.get(),
                List.of(Link.of("http://localhost:8080/bank/employees/employeeId")));
    }


    @ApiOperation(value="Update an employee", notes = "Used to update an employee with certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 201, message = "Created", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{employeeId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO newEmployee, @PathVariable Long employeeId) {
        log.info("UPDATE-ing employee with id: {}", employeeId);
        Optional<EmployeeDTO> result = service.getEmployeeById(employeeId);
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
                    Optional<EmployeeDTO> dto = Optional.ofNullable(
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
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{employeeId}", produces = {"application/json"})
    public EntityModel<EmployeeDTO> deleteEmployee(@PathVariable Long employeeId) {
        log.info("DELETE-ing bank account {}", employeeId);
        Optional<EmployeeDTO> result = service.deleteEmployee(employeeId);
        if (result.isEmpty()) {
            throw new BankException("Could not delete employee with id: " + employeeId);
        }
        return EntityModel.of(
                result.get(),
                List.of(Link.of("http://localhost:8080/bank/employees/")));
    }
}
