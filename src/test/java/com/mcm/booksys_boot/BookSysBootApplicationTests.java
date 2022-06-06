package com.mcm.booksys_boot;

import com.hyn.booksys_boot.service.RecordService;
import com.wry333.booksys_boot.dao.RecordDao;
import com.wry333.booksys_boot.dao.UserDao;
import com.wry333.booksys_boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BookSysBootApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    RecordDao recordDao;

    @Autowired
    UserService userService;

    @Autowired
    RecordService recordService;

    //@Test
//    void contextLoads() {
//        User user = new User();
//        user.setEmail("12345@china.gov.cn");
//        user.setPassword("1234");
//        userDao.login(user);
//    }
//
//    @Test
//    void contextLoad() throws Exception {
//        User user = new User();
//        Date now = new Date();
//        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//        user.setId(1);
//        for (String s : recordDao.getReturnDate(user)) {
//            Date data = myFormat.parse(s);
//            System.out.println(now.compareTo(data) + "    " + data + "    " + now);
//        }
//    }
//
//    @Test
//    void test() {
//        User user = new User();
//        user.setId(1);
//        userDao.resetPwd(user, "12345");
//    }
//
//
//    @Test
//    void test_page() {
//        PageHelper.startPage(1, 2);
//        User user = new User();
//        user.setId(1);
//        List<Record> list = recordDao.getRecordByUid(user);
//        for (Record r : list) {
//            System.out.println(r);
//        }
//    }
//
//    @Test
//    void test_page2() {
//        List<User> user = userService.getAllUser(Integer.parseInt("1"));
//        System.out.println(user);
//    }
}
