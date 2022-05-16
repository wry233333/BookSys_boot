package com.wry333.booksys_boot.filter;

import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter("/*")
public class AdminFilter implements Filter {
    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        String uri = http.getRequestURI();
        if (uri.contains("/admin/user") || uri.equals("/admin")) {
            User user = (User) http.getSession().getAttribute("user");
            if (userService.equ_admin(user)) {
                chain.doFilter(request, response);
            } else {
                http.getRequestDispatcher("/user_admin").forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
