package org.masa.ayanoter.logic;

import org.masa.ayanoter.dataAccess.User;
import org.masa.ayanoter.dataAccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mikle on 12/27/17.
 */
@Service
public class UserManager implements IUserManager {
    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id){
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids){
        return userRepository.findAllByIdIn(ids);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
