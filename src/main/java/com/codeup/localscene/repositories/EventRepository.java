package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long>{
    List<Events> findByBand(Band band);
}
