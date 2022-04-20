package com.wry333.booksys_boot;

import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.domian.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookSysBootApplicationTests {
    @Autowired
    UserDao userDao;

    //@Test
    void contextLoads() {
        User user = new User();
        user.setEmail("12345@china.gov.cn");
        user.setPassword("1234");
        userDao.login(user);
    }

}
