package com.hyn.booksys_boot.service;

import com.github.pagehelper.PageInfo;
import com.wry333.booksys_boot.domain.Record;

import java.util.Map;

public interface RecordService {
    PageInfo<Record> getAllRecord(int page);

    boolean saveRecord(Map map);

    void sendNotifyEmail(long parseLong);

    void removeRecord(long parseLong);

    void updateRecord(Record record);

    PageInfo<Record> getUserRecord(int parseInt, long parseLong);

    void delay(Map map);
}
