package com.wry333.booksys_boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.wry333.booksys_boot.dao.RecordDao;
import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private UserDao userDao;


    //主页显示借阅记录表
    @Override
    public List<Record> findAllRecord() {
        PageHelper.startPage(1, 5);
        List<Record> list = recordDao.findAll();
        for (Record r : list) {
            if (r.getRel_date() == null) {
                r.setStatue("未归还");
            } else {
                r.setStatue("已归还");
            }
            r.setUsername(userDao.findUserById(r.getId()));
        }
        return list;
    }
}
