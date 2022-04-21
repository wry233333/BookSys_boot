package com.wry333.booksys_boot;

import com.wry333.booksys_boot.dao.RecordDao;
import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class BookSysBootApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    RecordDao recordDao;

    //@Test
    void contextLoads() {
        User user = new User();
        user.setEmail("12345@china.gov.cn");
        user.setPassword("1234");
        userDao.login(user);
    }

    @Test
    void contextLoad() throws Exception {
        User user = new User();
        Date now = new Date();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.setId(1);
        for (String s : recordDao.getReturnDate(user)) {
            Date data = myFormat.parse(s);
            System.out.println(now.compareTo(data) + "    " + data + "    " + now);
        }
    }

    @Test
    void test() {
        User user = new User();
        user.setId(1);
        userDao.resetPwd(user, "12345");
    }

}
