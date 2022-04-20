package com.wry333.booksys_boot.domian;


import lombok.Data;

@Data
public class Book {
    private long b_id;
    private String name;
    private String publisher;
    private String author;
    private String date;
    private String info;
    private String context;
    private int c_id;
}
