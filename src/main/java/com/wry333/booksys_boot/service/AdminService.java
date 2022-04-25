package com.wry333.booksys_boot.service;

import com.wry333.booksys_boot.domain.Record;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    List<Record> findAllRecord();
}
