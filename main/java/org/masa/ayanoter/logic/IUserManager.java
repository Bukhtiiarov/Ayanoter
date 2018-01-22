package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.User;

import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
public interface IUserManager {
    User get(int id);

    List<User> getUsersByIds(List<Integer> ids);

    User getByLogin(String login);
}
