package org.masa.ayanoter.dataAccess;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mikle on 12/20/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    List<User> findAllByIdIn(List<Integer> ids);
}
