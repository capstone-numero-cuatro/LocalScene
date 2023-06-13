package com.codeup.localscene.repositories;

import com.codeup.localscene.models.BandUser;
import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BandUserRepository extends JpaRepository<BandUser, Long> {
    List<BandUser> findByUser(@Param("user") Users user);
}
