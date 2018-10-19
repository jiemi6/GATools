package com.minkey.filter;

import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆过滤
 */
@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //Minkey 不需要登录就可以访问的路径(比如:注册登录等)
    final String[] includeUrls = new String[]{
            "/license.html",
            "/license/upFile",
            "/license/getCode",

            "login.html"
    };

    @Value("${system.debug}")
    private boolean isDebug;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isDebug){
            User user = new User();
            user.setUid(1l);
            user.setuName("Filter_test");
            request.getSession().setAttribute("user",user);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String uri = request.getRequestURI();
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //如果已经登陆
        if (checkSession(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String requestType = request.getHeader("X-Requested-With");
        //判断是否是ajax请求
        if(requestType!=null && "XMLHttpRequest".equals(requestType)){
            response.getWriter().write(JSONMessage.createFalied(this.NO_LOGIN).toString());
        }else{
            //重定向到登录页(需要在static文件夹下建立此html文件)
            response.sendRedirect(request.getContextPath()+"/user/login.html");
        }
        return;
    }

    private boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }


    private boolean checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // session中包含user对象,则是登录状态
        if(session != null && session.getAttribute("user") != null){
            return true;
        }

        return false;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}