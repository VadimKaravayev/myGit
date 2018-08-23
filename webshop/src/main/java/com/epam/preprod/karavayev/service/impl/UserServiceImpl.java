package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import com.epam.preprod.karavayev.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        if (Objects.nonNull(userDao.getUserByEmail(user.getEmail()))) {
            throw new UserAlreadyExistsException("User already exists by email -> " + user.getEmail());
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userDao.create(user);
    }
}
