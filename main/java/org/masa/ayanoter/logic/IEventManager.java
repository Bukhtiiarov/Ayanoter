package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.Event;
import org.masa.ayanoter.dataAccess.Post;
import org.masa.ayanoter.dataAccess.Repost;
import org.masa.ayanoter.dataAccess.User;

import java.util.List;

/**
 * Created by mikle on 12/25/17.
 */
public interface IEventManager {
    List<Event> getEventsOfUsers(List<User> users);
}
