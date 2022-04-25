package com.wry333.booksys_boot.filter;


import com.wry333.booksys_boot.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter("/*")
public class LoginFilter implements Filter {


    /**
     * 用于登录验证
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.contains("/sign_in") || uri.contains("/user_login") || uri.contains("/sign_up")
                || uri.contains("/register") || uri.contains("/plugins") ||
                uri.contains("/index") || uri.contains("/img") || uri.contains("/css") || uri.equals("/")) {
            chain.doFilter(request, response);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                req.getRequestDispatcher("/sign_in").forward(req, resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
