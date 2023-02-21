package com.idstar.lms.api.repositories.users;


import com.idstar.lms.api.generic.GenericRepo;
import com.idstar.lms.api.model.user.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends GenericRepo<Users> {
    Optional<Users> findByUsername(String username);

}
