package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.Subscription;
import org.masa.ayanoter.dataAccess.User;

import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
public interface ISubscriptionManager {
    List<Subscription> getUserSubscriptions(User user);

    boolean isSubscribed(User from, User to);

    Subscription subscribe(User user, int targetUserId);

    void unsubscribe(User user, int targetUserId);
}
