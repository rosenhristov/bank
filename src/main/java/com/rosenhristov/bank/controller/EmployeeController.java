package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.exception.ErrorStub;
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
@Api(value ="Operations related to employees", tags = {"Employees"})
@SwaggerDefinition(tags = {@Tag(name = "Employees", description = "Operations related to employees")})
public class EmployeeController {

    private final EmployeeService service;

    @ApiOperation(value="Retrieve all employees", notes = "Used to fetch all employees from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        log.info("GETting all employees");
        return ResponseEntity.of(Optional.of(service.getAll()));
    }


    @ApiOperation(value="Fetch an employee by id", notes = "Provide and id to lookup specific employee from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/{employeeId}", produces = {"application/json"})
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        log.info("GETting employee with id {}", employeeId);
        return ResponseEntity.of(service.getEmployeeById(employeeId));
    }


    @ApiOperation(value="Add a new employee", notes = "Used to insert new employee in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 201, message = "Created", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO newEmployee) {
        log.info("INSERTing employee {} {}",
                newEmployee.getName(),
                newEmployee.getSurname());
        return ResponseEntity.of(
                Optional.ofNullable(service.save(
                        service.getMapper().toEntity(newEmployee))));
    }


    @ApiOperation(value="Update an employee", notes = "Used to update an employee with certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 201, message = "Created", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PutMapping(value = "/{employeeId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO newEmployee, @PathVariable Long employeeId) {
        log.info("UPDATE-ing employee with id = {}", employeeId);
        return ResponseEntity.of(
                service.getEmployeeById(employeeId)
                        .map(employee -> {
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
                            return service.save(
                                    service.getMapper().toEntity(employee));
                        }));
    }


    @ApiOperation(value="Delete an employee with indicated id", notes = "Used to delete an employee by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @DeleteMapping(value = "/{employeeId}", produces = {"application/json"})
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long employeeId) {
        log.info("DELETE-ing bank account {}", employeeId);
        return ResponseEntity.of(
                service.deleteEmployee(employeeId));
    }
}
