package com.shaked.blog.repository;

import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.shaked.blog.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by USER1 on 1/26/2018.
 */
@Repository
public interface BlogRepository extends ArangoRepository<Blog> {
    @Query("FOR e \n" +
            "    IN blog \n" +
            "    SORT e.voteCount / POW(DATE_DIFF(DATE_NOW(), e.created,\"H\") + 1,2) DESC\n" +
            "    LIMIT @limit\n" +
            "    RETURN e")
    Collection<Blog> getTopXBlogs(@Param("limit") int x);
}
