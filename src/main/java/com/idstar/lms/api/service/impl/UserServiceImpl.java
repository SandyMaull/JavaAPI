package com.idstar.lms.api.service.impl;

import com.idstar.lms.api.exceptions.EmployeeNotExistsException;
import com.idstar.lms.api.exceptions.InvalidInputParameterExceptions;
import com.idstar.lms.api.exceptions.UserAlreadyExistsException;
import com.idstar.lms.api.exceptions.UserNotExistsException;
import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.repositories.users.UserRepo;
import com.idstar.lms.api.model.user.Users;
import com.idstar.lms.api.model.user.Role;
import com.idstar.lms.api.service.EmployeeService;
import com.idstar.lms.api.service.UserService;
import com.idstar.lms.api.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final EmployeeService employeeService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employees> getEmployees(String username) {
        if (username != null) {
            Optional<Users> optional = userRepo.findByUsername(username);
            Users user = optional.orElseThrow(() -> {throw new UserNotExistsException(username);});

            return user.getEmployees();
        } else {
            throw new InvalidInputParameterExceptions("invalid username");
        }
    }

    @Override
    public Users addEmployeesToUserByAdmin(List<Long> guitarsId, String username) {
        if (guitarsId != null && username != null && !username.isEmpty()) {
            Optional<Users> optional = userRepo.findByUsername(username);
            Users user = optional.orElseThrow(() -> {throw new UserNotExistsException(username);});

            guitarsId.forEach(id -> {
                user.getEmployees().add(employeeService.getById(id));
            });
            return userRepo.save(user);
        } else {
            throw new InvalidInputParameterExceptions("invalid input parameter");
        }
    }

    @Override
    public List<Users> addAllUsers(List<Users> userList) {
        if (userList != null) {
            return (List<Users>) userRepo.saveAll(userList);
        } else {
            return null;
        }
    }

    @Override
    public Users addEmployeesToUserByUser(List<Long> guitarsId, String authorizationHeader) {

        String username = TokenUtils.getInstance().getUsernameFromToken(authorizationHeader);

        if (guitarsId != null && username != null && !username.isEmpty()) {
            Optional<Users> optional = userRepo.findByUsername(username);
            Users user = optional.orElseThrow(() -> {throw new UserNotExistsException(username);});

            guitarsId.forEach(id -> {
                if (employeeService.getById(id) != null) {
                    user.getEmployees().add(employeeService.getById(id));
                } else {
                    throw new EmployeeNotExistsException("guitar: " + id + " doesn't exists");
                }
            });
            return userRepo.save(user);
        } else {
            throw new InvalidInputParameterExceptions("invalid input parameter");
        }
    }

    public Users addUser(Users user) {
        log.info("Saving user {} to the db", user.getUsername());

        if (user.getUsername() != null && user.getPassword() != null && !user.getUsername().isEmpty()) {
            if (userRepo.findByUsername(user.getUsername()).isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));

                return userRepo.save(user);
            } else {
                throw new UserAlreadyExistsException(user.getUsername());
            }
        } else {
            throw new InvalidInputParameterExceptions("username or password are unavailable");
        }
    }

    public Users addRoleToUser(String username, Role role) {
        log.info("Set role {} to the user {}", role, username);

        if (username != null && !username.isEmpty() && role != null) {
            Users user = userRepo.findByUsername(username).orElseThrow(() -> {throw new UserNotExistsException(username);});
            user.getRoles().add(role);
            return userRepo.save(user);
        }  else {
            throw new InvalidInputParameterExceptions("username or role are unavailable");
        }
    }

    public Users getUser(String username) {
        log.info("Get user {}", username);

        if (username != null && !username.isEmpty()) {
            Optional<Users> optional = userRepo.findByUsername(username);
            return optional.orElseThrow(() -> {throw new UserNotExistsException(username);});
        } else {
            throw new InvalidInputParameterExceptions("username are null or empty");
        }
    }

    public List<Users> getAllUsers() {
        return (List<Users>) userRepo.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user {}", username);

        Optional<Users> optional = userRepo.findByUsername(username);
            Users user = optional.orElseThrow(() -> {throw new UserNotExistsException(username);});
            return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}