package org.masa.ayanoter.dataAccess;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mikle on 12/25/17.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findByAuthorInOrderByDateDesc(List<User> authors);
}
