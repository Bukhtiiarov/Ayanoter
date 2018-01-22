package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.Subscription;
import org.masa.ayanoter.dataAccess.SubscriptionRepository;
import org.masa.ayanoter.dataAccess.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
@Service
public class SubscriptionManager implements ISubscriptionManager {
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionManager(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> getUserSubscriptions(User user){
        return subscriptionRepository.findByFromUser(user.getId());
    }

    @Override
    public boolean isSubscribed(User from, User to){
        return subscriptionRepository.findByFromUserAndToUser(from.getId(), to.getId()) != null;
    }

    @Override
    public Subscription subscribe(User user, int targetUserId){
        Subscription subscription = new Subscription();
        subscription.setFromUser(user.getId());
        subscription.setToUser(targetUserId);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribe(User user, int targetUserId){
        subscriptionRepository.deleteByFromUserAndToUser(user.getId(), targetUserId);
    }
}
