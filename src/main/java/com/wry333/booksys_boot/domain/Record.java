package com.wry333.booksys_boot.domain;


import lombok.Data;

@Data
public class Record {
    private long l_id;
    private long id;
    private long b_id;
    private String return_date;
    private String borrow_date;
    private String rel_date;
    private String b_name;
    private String statue;
    private String username;
}
