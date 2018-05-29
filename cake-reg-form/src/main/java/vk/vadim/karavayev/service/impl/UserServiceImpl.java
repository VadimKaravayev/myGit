package vk.vadim.karavayev.service.impl;

import vk.vadim.karavayev.entity.User;
import vk.vadim.karavayev.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private List<User> userList;

    public UserServiceImpl(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean register(User user) {
        return !userList.contains(user) && userList.add(user);
    }
}
