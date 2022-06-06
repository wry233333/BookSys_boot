package com.hyn.booksys_boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyn.booksys_boot.service.RecordService;
import com.wry333.booksys_boot.dao.BookDao;
import com.wry333.booksys_boot.dao.RecordDao;
import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private SimpleDateFormat myFormat;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<Record> getAllRecord(int page) {
        PageHelper.startPage(page, 10);
        List<Record> all = recordDao.findAll();
        Date now = new Date();
        for (Record item : all) {
            item.setB_name(bookDao.findNameById(item.getB_id()));
            item.setUsername(userDao.findUserById(item.getId()));
            if (item.getRel_date() == null) {
                try {
                    if (now.getTime() >= myFormat.parse(item.getReturn_date()).getTime()) {
                        item.setStatue("超时未归还");
                    } else {
                        item.setStatue("未归还");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                item.setStatue("已归还");
            }
        }
        PageInfo<Record> pageInfo = new PageInfo<Record>(all);
        return pageInfo;
    }

    @Override
    public boolean saveRecord(Map map) {
        Record record = new Record();
        record.setId(Long.parseLong((String) map.get("id")));
        record.setB_id(Long.parseLong((String) map.get("b_id")));
        Date date = new Date();
        record.setBorrow_date(myFormat.format(date.getTime()));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, Integer.parseInt((String) map.get("time")));
        record.setReturn_date(myFormat.format(c.getTime()));
        recordDao.saveRecord(record);
        return false;
    }

    @Override
    public void sendNotifyEmail(long parseLong) {
        User user = userDao.findUserById2(parseLong);
        MailUtils.sendMail(user.getEmail(), "您有书籍超时未归还。。。。。。请及时前往平台处理", "超时未归还提醒邮件");
    }

    @Override
    public void removeRecord(long parseLong) {
        recordDao.deleteRecord(parseLong);
    }

    @Override
    public void updateRecord(Record record) {
        recordDao.updateRecord(record);
    }

    @Override
    public PageInfo<Record> getUserRecord(int parseInt, long parseLong) {
        PageHelper.startPage(parseInt, 10);
        User user = new User();
        user.setId(parseLong);
        List<Record> all = recordDao.getRecordByUid(user);
        Date now = new Date();
        for (Record item : all) {
            item.setB_name(bookDao.findNameById(item.getB_id()));
            item.setUsername(userDao.findUserById(item.getId()));
            if (item.getRel_date() == null) {
                try {
                    if (now.getTime() >= myFormat.parse(item.getReturn_date()).getTime()) {
                        item.setStatue("超时未归还");
                    } else {
                        item.setStatue("未归还");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                item.setStatue("已归还");
            }
        }
        PageInfo<Record> pageInfo = new PageInfo<Record>(all);
        return pageInfo;
    }

    @Override
    public void delay(Map map) {

    }
}
