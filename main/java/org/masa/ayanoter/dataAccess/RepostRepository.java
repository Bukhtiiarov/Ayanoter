package org.masa.ayanoter.dataAccess;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by mikle on 12/25/17.
 */
public interface RepostRepository extends CrudRepository<Repost, Integer> {
    Repost findByPostAndAuthor(Post post, User author);
    long countByPost_User(User user);
}
