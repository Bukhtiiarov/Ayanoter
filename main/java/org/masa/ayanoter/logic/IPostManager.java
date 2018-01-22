package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.Post;
import org.masa.ayanoter.dataAccess.User;

/**
 * Created by mikle on 12/27/17.
 */
public interface IPostManager {
    //TODO: @Transactional?
    Post create(User author, String text);

    long getPostCountForUser(User user);
}
