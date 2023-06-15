package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.BandPosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BandPostRepository extends JpaRepository<BandPosts, Long> {
    List<BandPosts> findByBand(Band band);
}
