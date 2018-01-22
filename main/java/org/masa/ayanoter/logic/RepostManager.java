package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mikle on 12/27/17.
 */
@Service
public class RepostManager implements IRepostManager {
    private RepostRepository repostRepository;
    private EventRepository eventRepository;

    @Autowired
    public RepostManager(RepostRepository repostRepository,
                         EventRepository eventRepository) {
        this.repostRepository = repostRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public Repost create(User author, int postId){
        Post post = new Post();
        post.setId(postId);

        Repost repost = new Repost();
        repost.setPost(post);
        repost.setAuthor(author);

        repostRepository.save(repost);

        Event event = new Event();
        event.setAuthor(author);
        event.setType(EventType.Repost);
        event.setRepost(repost);

        eventRepository.save(event);

        return repost;
    }

    @Override
    public boolean canBeReposted(User user, Post post){
        boolean isCurrentUserPost = user.getId().equals(post.getUser().getId());
        boolean isAlreadyReposted = repostRepository.findByPostAndAuthor(post, user) != null;
        return !isCurrentUserPost && !isAlreadyReposted;
    }

    @Override
    public long getRepostsCount(User user){
        return repostRepository.countByPost_User(user);
    }
}
