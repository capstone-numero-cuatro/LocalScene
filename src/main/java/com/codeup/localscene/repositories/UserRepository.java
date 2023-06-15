package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByVerificationCode(String code);
    User findByResetPasswordToken(String token);
    User findByUsername(String username);
    List<User> findByBand(Band band);
}
