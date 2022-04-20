package com.wry333.booksys_boot.domian;


import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
}
