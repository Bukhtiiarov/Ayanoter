package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.Post;
import org.masa.ayanoter.dataAccess.Repost;
import org.masa.ayanoter.dataAccess.User;

/**
 * Created by mikle on 12/27/17.
 */
public interface IRepostManager {
    Repost create(User author, int postId);

    boolean canBeReposted(User user, Post post);

    long getRepostsCount(User user);
}
