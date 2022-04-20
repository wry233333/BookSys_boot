package com.wry333.booksys_boot.service.impl;

import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domian.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(User user) {
       return userDao.login(user);
    }

    @Override
    public boolean register(User user) {
        if (userDao.findByEmail(user) == null) {
            userDao.saveUser(user);
            return true;
        }
        return false;
    }
}
