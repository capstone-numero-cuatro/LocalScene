package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long>{

}
