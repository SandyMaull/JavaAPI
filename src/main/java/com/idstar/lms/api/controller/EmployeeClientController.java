package com.idstar.lms.api.controller;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.employees_client.EmployeesClient;
import com.idstar.lms.api.repositories.employees_client.EmployeesClientRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee_client")
public class EmployeeClientController extends GenericController<EmployeesClient> {

    public EmployeeClientController(EmployeesClientRepo s) {
        super(s);
    }
}