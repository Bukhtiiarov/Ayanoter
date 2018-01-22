package org.masa.ayanoter.presentation;

import org.masa.ayanoter.dataAccess.User;

import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
public interface IEventListViewModelProvider {
    List<EventViewModel> get(User newsOwner, User currentUser);

    List<EventViewModel> get(List<User> users, User currentUser);
}
