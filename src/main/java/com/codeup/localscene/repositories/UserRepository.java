package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByVerificationCode(String code);
    Users findByResetPasswordToken(String token);
    Users findByUsername(String username);
    Users findById(long id);
}
