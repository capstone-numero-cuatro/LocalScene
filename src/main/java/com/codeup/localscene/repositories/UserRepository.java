package com.codeup.localscene.repositories;

import com.codeup.localscene.model.BandUser;
import com.codeup.localscene.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByVerificationCode(String code);

    Users findByUsername(String username);
}
