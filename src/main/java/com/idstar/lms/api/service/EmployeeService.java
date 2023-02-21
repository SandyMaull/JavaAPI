package com.idstar.lms.api.service;

import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.model.user.Users;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employees add(Employees employees);
    Employees getById(Long id);
    Employees validate(Employees employees);
    Map<String, List<Object>> compareEmployees(List<Long> guitarIds);

    List<Users> findUsersByEmployeeId(Long id);

    List<Employees> searchByParameters(
            String model,
            Double priceFrom,
            Double priceTo,
            Integer yearFrom,
            Integer yearTo,
            String type,
            String design,
            String body,
            String colour,
            Integer fretsNumberFrom,
            Integer fretsNumberTo,
            Integer stringsNumberFrom,
            Integer stringsNumberTo,
            Integer pickupsNumberFrom,
            Integer pickupsNumberTo,
            String manufacturer
    );
}
