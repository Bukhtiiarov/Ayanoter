package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mikle on 12/27/17.
 */
@Service
public class PostManager implements IPostManager {
    private PostRepository postRepository;
    private EventRepository eventRepository;

    @Autowired
    public PostManager(PostRepository postRepository,
                       EventRepository eventRepository) {
        this.postRepository = postRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    //TODO: @Transactional?
    public Post create(User author, String text){
        Post post = new Post();
        post.setText(text);
        post.setUser(author);

        postRepository.save(post);

        Event event = new Event();
        event.setAuthor(author);
        event.setType(EventType.Post);
        event.setPost(post);

        eventRepository.save(event);

        return post;
    }

    @Override
    public long getPostCountForUser(User user){
        return postRepository.countByUser(user);
    }
}
