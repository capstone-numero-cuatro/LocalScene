package com.codeup.localscene.repositories;

import com.codeup.localscene.models.BandPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandPostRepository extends JpaRepository<BandPosts, Long> {

}
