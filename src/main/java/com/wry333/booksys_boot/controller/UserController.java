package com.wry333.booksys_boot.controller;


import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 返回登录页面
     *
     * @param mav
     * @return
     */
    @RequestMapping("/sign_in")
    public ModelAndView sign_in(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    /**
     * 返回注册页面
     *
     * @param mav
     * @return
     */

    @RequestMapping("/sign_up")
    public ModelAndView sign_up(ModelAndView mav) {
        mav.setViewName("register");
        return mav;
    }


    /**
     * 处理用户登录
     *
     * @param user    自动注入需要的登录的用户对象数据
     * @param mav
     * @param session 用于保存用户的session
     * @return
     */
    @RequestMapping("user_login")
    public ModelAndView user_login(User user, ModelAndView mav, HttpSession session) {
        User user_login = userService.login(user);
        if (user_login != null) {
            session.setAttribute("user", user_login);
            mav.setViewName("forward:/user_admin");
        } else {
            mav.setViewName("login");
            mav.addObject("log_msg", "邮箱或密码错误");
        }
        return mav;
    }


    /**
     * 处理用户注册
     *
     * @param user 自动注入用户输入数据
     * @param mav
     * @return
     */

    @RequestMapping("/register")
    public ModelAndView user_register(User user, ModelAndView mav) {
        mav.setViewName("register");
        if (userService.register(user)) {
            mav.setViewName("forward:login");
            mav.addObject("log_msg", "注册成功，请登录");
        } else {
            mav.addObject("reg_msg", "邮箱地址重复，请重试");
        }
        return mav;
    }

    /**
     * 用户主页数据与视图结合的controller
     *
     * @param mav
     * @param user 自动注入已经登录的用户
     * @return
     * @throws Exception 抛出时间处理异常
     */
    @RequestMapping("/user_admin")
    public ModelAndView user_admin(ModelAndView mav, @SessionAttribute User user) throws Exception {
        mav.setViewName("user_index");
        List<Integer> list = userService.get_index_data(user);
        List<Record> records = userService.getAllRecord(user);
        mav.addObject("num", list);
        mav.addObject("records", records);
        return mav;
    }

    /**
     * 处理用户退出登录
     *
     * @param session 用于删除用户数据的session
     * @return
     */
    @RequestMapping("log_out")
    public String log_out(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index.html";
    }


    /**
     * 返回一个空页面，开发专用，部署时应删掉
     *
     * @return
     */

    @RequestMapping("/test")
    public String test() {
        return "whitepage";
    }


    /**
     * 返回重置密码页面
     *
     * @return
     */
    @RequestMapping("/reset")
    public String reset() {
        return "resetPassword";
    }

    /**
     * 处理用户重置密码请求
     *
     * @param pwd     用户输入的原密码
     * @param new_pwd 新密码
     * @param session 更新session
     * @param mav
     * @return
     */

    @RequestMapping("/resetPwd")
    public ModelAndView reset_pwd(@RequestParam("password") String pwd, @RequestParam("new") String new_pwd, HttpSession session, ModelAndView mav) {
        User user = (User) session.getAttribute("user");
        if (user.getPassword().equals(pwd)) {
            userService.resetPwd(user, new_pwd);
            session.removeAttribute("user");
            mav.setViewName("redirect:sign_in");
        } else {
            mav.setViewName("resetPassword");
            mav.addObject("msg", "原密码错误，请重试");
        }
        return mav;
    }


    /**
     * 返回修改用户名的页面
     *
     * @return
     */
    @RequestMapping("/rename")
    public String rename() {
        return "rename";
    }

    /**
     * 处理用户重命名的请求
     *
     * @param mav
     * @param username 新用户名
     * @param user     session中用户对象
     * @param session  更新session
     * @return
     */

    @RequestMapping("/user_rename")
    public ModelAndView user_rename(ModelAndView mav, @RequestParam String username, @SessionAttribute User user, HttpSession session) {
        mav.setViewName("rename");
        user.setUsername(username);
        session.setAttribute("user", user);
        userService.rename(user, username);
        mav.addObject("msg", "修改成功");
        return mav;
    }

    /**
     * 返回管理员主页
     *
     * @return
     */
    @RequestMapping("/admin")
    public ModelAndView admin(ModelAndView mav) throws Exception {
        mav.setViewName("/admin/admin_index");
        List list = userService.get_admin_data();
        mav.addObject("list", list);

        return mav;
    }

    /**
     * 返回用户管理页面
     *
     * @return
     */
    @RequestMapping("/edit_user")
    public String edit_user() {
        return "/admin/admin_user";
    }


    /**
     * 返回修改用户界面
     */
    @RequestMapping("/admin/adj_user/{_id}")
    public ModelAndView adj_user(ModelAndView mav, @PathVariable String _id) {
        long id = Long.parseLong(_id);
        User user = new User();
        if (id != -1) {
            user = userService.getGetUser(id);
        }
        mav.setViewName("/admin/admin_userInfo");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/admin/saveUserChange")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/edit_user";
    }


    @RequestMapping("/findUserPwd")
    public String findUserPwd() {
        return "findPassword";
    }

    @RequestMapping("/changePwd")
    public ModelAndView changePwd(ModelAndView mav, HttpSession session, String email, String code, String password) {
        String code_S = (String) session.getAttribute("code");
        if (code.equals(code_S)) {
            userService.finPwd(email, password);
            mav.setViewName("forward:/sign_in");
        } else {
            mav.setViewName("findPassword");
            mav.addObject("msg", "修改失败");
        }
        return mav;
    }
}
