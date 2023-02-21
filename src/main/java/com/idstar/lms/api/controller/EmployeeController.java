package com.idstar.lms.api.controller;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.repositories.employees.EmployeesRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends GenericController<Employees> {

    public EmployeeController(EmployeesRepo s) {
        super(s);
    }
}