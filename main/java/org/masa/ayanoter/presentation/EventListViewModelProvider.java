package org.masa.ayanoter.presentation;

import org.masa.ayanoter.dataAccess.Event;
import org.masa.ayanoter.dataAccess.Repost;
import org.masa.ayanoter.dataAccess.User;
import org.masa.ayanoter.logic.IEventManager;
import org.masa.ayanoter.logic.IRepostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
@Service
public class EventListViewModelProvider implements IEventListViewModelProvider {
    private IEventManager eventManager;
    private IRepostManager repostManager;

    @Autowired
    public EventListViewModelProvider(IEventManager eventManager,
                                      IRepostManager repostManager) {
        this.eventManager = eventManager;
        this.repostManager = repostManager;
    }

    @Override
    public List<EventViewModel> get(User newsOwner, User currentUser){
        List<User> users = new ArrayList<>();
        users.add(newsOwner);
        return get(users, currentUser);
    }

    @Override
    public List<EventViewModel> get(List<User> users, User currentUser){
        List<Event> rawEvents = eventManager.getEventsOfUsers(users);

        List<EventViewModel> events = new ArrayList<>();
        for (Event rawEvent: rawEvents) {
            EventViewModel event = new EventViewModel();
            event.id = rawEvent.getId();
            event.authorName = rawEvent.getAuthor().getLogin();
            switch (rawEvent.getType()){
                case Post:
                    event.post = new EventPostViewModel();
                    event.post.id = rawEvent.getPost().getId();
                    event.post.text = rawEvent.getPost().getText();
                    event.canBeReposted = repostManager.canBeReposted(currentUser, rawEvent.getPost());
                    break;
                case Repost:
                    Repost repost = rawEvent.getRepost();
                    event.repost = new EventRepostViewModel();
                    event.repost.id = repost.getId();
                    event.repost.postAuthorId = repost.getPost().getUser().getId();
                    event.repost.postAuthorLogin = repost.getPost().getUser().getLogin();
                    event.repost.postId = repost.getPost().getId();
                    event.repost.postText = repost.getPost().getText();
                    break;
            }
            events.add(event);
        }

        return events;
    }
}
