package com.codeup.localscene.repositories;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByUser(Users user);

}
