package com.idstar.lms.api.service.impl;

import com.idstar.lms.api.exceptions.EmployeeNotExistsException;
import com.idstar.lms.api.exceptions.InvalidInputParameterExceptions;
import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.repositories.employees.EmployeesRepo;
import com.idstar.lms.api.model.user.Users;
import com.idstar.lms.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeesRepo employeesRepo;



    @Override
    public Employees getById(Long id) {
        if (id != null) {
            Optional<Employees> response = employeesRepo.findById(id);
            if (response.isPresent()) {
                return response.get();
            } else {
                throw new EmployeeNotExistsException(
                        "Employee with id " + id + " doesn't exist");
            }
        } else {
            throw new InvalidInputParameterExceptions("invalid employee id");
        }
    }

    @Override
    public Employees add(@Valid Employees employees) {
        return employeesRepo.save(employees);
    }

    public Employees validate(Employees employees) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Employees>> violations = validator.validate(employees);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        } else {
            return employees;
        }
    }

    @Override
    public Map<String, List<Object>> compareEmployees(List<Long> guitarIds) {
        return null;
    }

    @Override
    public List<Users> findUsersByEmployeeId(Long id) {
        return null;
    }

    @Override
    public List<Employees> searchByParameters(String model, Double priceFrom, Double priceTo, Integer yearFrom, Integer yearTo, String type, String design, String body, String colour, Integer fretsNumberFrom, Integer fretsNumberTo, Integer stringsNumberFrom, Integer stringsNumberTo, Integer pickupsNumberFrom, Integer pickupsNumberTo, String manufacturer) {
        return null;
    }

}
