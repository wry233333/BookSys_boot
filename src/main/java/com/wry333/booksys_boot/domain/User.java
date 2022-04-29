package com.wry333.booksys_boot.domain;


import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private User_Level level;
}
