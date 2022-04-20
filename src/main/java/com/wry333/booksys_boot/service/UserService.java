package com.wry333.booksys_boot.service;

import com.wry333.booksys_boot.domian.User;

public interface UserService {
    User login(User user);

    boolean register(User user);
}
