package com.idstar.lms.api.service;

import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.model.user.Users;
import com.idstar.lms.api.model.user.Role;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();
    Users getUser(String username);
    List<Employees> getEmployees(String username);
    Users addUser(Users users);

    List<Users> addAllUsers(List<Users> userList);
    Users addRoleToUser(String username, Role role);
    Users addEmployeesToUserByAdmin(List<Long> guitarsId, String username);
    Users addEmployeesToUserByUser(List<Long> guitarsId, String authorizationHeader);
}
