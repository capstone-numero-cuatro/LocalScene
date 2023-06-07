package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Bands, Long> {
    List<Bands> findByUser(Users user);
}
