package org.masa.ayanoter;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.masa.ayanoter.dataAccess.*;
import org.masa.ayanoter.logic.IEventManager;
import org.masa.ayanoter.logic.IPostManager;
import org.masa.ayanoter.logic.IRepostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by mikle on 12/4/17.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    IEventManager eventManager;

    @Autowired
    IPostManager postManager;

    @Autowired
    IRepostManager repostManager;

    @Override
    public void run(String... strings) throws Exception {
        User vasya = new User();
        vasya.setLogin("Vasya");
        vasya.setPassword("123");
        userRepository.save(vasya);

        User petya = new User();
        petya.setLogin("Petya");
        petya.setPassword("123");
        userRepository.save(petya);

        User hanna = new User();
        hanna.setLogin("Hanna");
        userRepository.save(hanna);

        postManager.create(petya, "Hello!");

        Post vasyasPost = postManager.create(vasya, "Vasya's post!");
        repostManager.create(petya, vasyasPost.getId());

        Subscription vasyaToPetya = new Subscription();
        vasyaToPetya.setFromUser(vasya.getId());
        vasyaToPetya.setToUser(petya.getId());
        subscriptionRepository.save(vasyaToPetya);

        Subscription petyaToHanna = new Subscription();
        petyaToHanna.setFromUser(petya.getId());
        petyaToHanna.setToUser(hanna.getId());
        subscriptionRepository.save(petyaToHanna);

        Subscription hannaToVasya = new Subscription();
        hannaToVasya.setFromUser(hanna.id);
        hannaToVasya.setToUser(vasya.id);
        subscriptionRepository.save(hannaToVasya);
    }
}
