package com.idstar.lms.api.components;

import com.idstar.lms.api.model.user.Users;
import com.idstar.lms.api.model.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public UserDTO toUserVO(Users user) {
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getRoles(), user.getEmployees());
    }
}

