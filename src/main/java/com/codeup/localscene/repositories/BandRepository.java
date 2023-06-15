package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    Band findByBandname(String bandname);

}
