package com.idstar.lms.api.model.user;

import com.idstar.lms.api.model.employees.Employees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO extends Users {

    private Long id;
    private String name;
    private String username;
    private Set<Role> roles;
    private List<Employees> employees;
}
