package com.wry333.booksys_boot.service.impl;

import com.github.pagehelper.PageHelper;
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
    private SimpleDateFormat myFormat;

    @Autowired
    UserDao userDao;

    @Autowired
    RecordDao recordDao;

    @Autowired
    BookDao bookDao;

    /**
     * 登录实现
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.login(user);
    }


    /**
     * 注册实现
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        if (userDao.findByEmail(user) == null) {
            userDao.saveUser(user);
            return true;
        }
        return false;
    }

    /**
     * 查询首页各种数据
     *
     * @param user
     * @return
     * @throws Exception
     */

    @Override
    public List<Integer> get_index_data(User user) throws Exception {
        int i = 0;
        List<Integer> object = new ArrayList<Integer>();
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


    /**
     * 查询当前登录用户的所有借阅记录
     *
     * @param user
     * @return
     */

    @Override
    public List<Record> getAllRecord(User user) {
        PageHelper.startPage(1, 5);
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


    /**
     * 重置密码业务的实现
     *
     * @param user
     * @param new_pwd
     * @return
     */
    @Override
    public boolean resetPwd(User user, String new_pwd) {
        userDao.resetPwd(user, new_pwd);
        return true;
    }


    /**
     * 重命名用户名的实现
     *
     * @param user
     * @param username
     */
    @Override
    public void rename(User user, String username) {
        userDao.rename(user.getId(), username);
    }

    /**
     * 获得管理员登录页面的数据
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> get_admin_data() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        Date now = new Date();
        list.add(bookDao.findAllBookNum());
        list.add(userDao.findAllUserNum());
        for (String s : recordDao.getAllReturnDate()) {
            Date data = myFormat.parse(s);
            if (now.compareTo(data) == 1) i++;
        }
        list.add(i);
        int record = recordDao.getAllNum();
        if (record != 0) {
            float f = (float) i / (float) record;
            f = f * 100;
            list.add((int) f);
        } else {
            list.add(0);
        }
        return list;
    }

    /**
     * 分页查询每五条用户数据
     *
     * @param i
     * @return
     */
    @Override
    public List<User> getAllUser(int i) {
        PageHelper.startPage(i, 5);
        List<User> list = userDao.findAllUsers();
        return list;
    }

    /**
     * 通过list数组批量删除用户
     *
     * @param list_id
     */

    @Override
    public void deleteUser(List<String> list_id) {
        for (String user_id : list_id) {
            userDao.deleteUserById(user_id);
        }
    }

    /**
     * 通过用户查找用户
     *
     * @param username
     * @return
     */

    @Override
    public List<User> searchUser(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public User getGetUser(long id) {
        return userDao.findUserById2(id);
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() == -1) {
            userDao.saveUser(user);
        }
        userDao.updateUser(user);
    }
}
