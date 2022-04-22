package com.wry333.booksys_boot.service;

import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;

import java.util.List;

public interface UserService {
    User login(User user);

    boolean register(User user);


    List<Integer> get_index_data(User user) throws Exception;

    List<Record> getAllRecord(User user);

    boolean resetPwd(User user, String new_pwd);

    void rename(User user, String username);
}
