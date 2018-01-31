package com.shaked.blog.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.shaked.blog.model.Vote;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by USER1 on 1/28/2018.
 */
@Repository
public interface VoteRepository extends ArangoRepository<Vote> {
    Optional<Vote> findByUserAndBlog(String user, String blog);
}
