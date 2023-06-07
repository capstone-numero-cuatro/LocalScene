package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Bands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Bands, Long> {

}
