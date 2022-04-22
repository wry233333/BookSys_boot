package com.wry333.booksys_boot.service.impl;

import com.wry333.booksys_boot.dao.BookDao;
import com.wry333.booksys_boot.dao.RecordDao;
import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RecordDao recordDao;

    @Autowired
    BookDao bookDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public boolean register(User user) {
        if (userDao.findByEmail(user) == null) {
            userDao.saveUser(user);
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> get_index_data(User user) throws Exception {
        int i = 0;
        List<Integer> object = new ArrayList<Integer>();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        int sum = recordDao.getNumByUid(user);
        object.add(sum);
        int rel = recordDao.getShouldReturn(user);
        object.add(rel);
        for (String s : recordDao.getReturnDate(user)) {
            Date data = myFormat.parse(s);
            if (now.compareTo(data) == 1) i++;
        }
        object.add(i);
        if (sum != 0) {
            float f = (float) i / (float) sum;
            f = f * 100;
            System.out.println(f);
            int j = (int) f;
            object.add(j);
        }
        return object;
    }


    @Override
    public List<Record> getAllRecord(User user) {
        List<Record> list = recordDao.getRecordByUid(user);
        for (Record record : list) {
            if (record.getRel_date() == null) record.setStatue("未归还");
            else {
                record.setStatue("已归还");
            }
            record.setB_name(bookDao.findNameById(record.getB_id()));
        }
        return list;
    }

    @Override
    public boolean resetPwd(User user, String new_pwd) {
        userDao.resetPwd(user, new_pwd);
        return true;
    }

    @Override
    public void rename(User user, String username) {
        userDao.rename(user.getId(), username);
    }
}
