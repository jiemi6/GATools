package com.minkey.filter;

import com.minkey.contants.ErrorCodeEnum;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登陆过滤，第二个执行
 */
@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    private final String no_login = "/login.html";

    protected  List<Pattern> patterns = new ArrayList<Pattern>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile(no_login));
        patterns.add(Pattern.compile("/lg2.html"));

        patterns.add(Pattern.compile(LicenseFilter.no_license));

        patterns.add(Pattern.compile("/user/getVCode"));
        patterns.add(Pattern.compile("/user/checkVCode"));
        patterns.add(Pattern.compile("/user/login"));

        patterns.add(Pattern.compile(".*[(\\.css )(\\.json )(\\.png )(\\.gif )(\\.js )(\\.eot )(\\.svg )(\\.ttf )(\\.woff )(\\.mp4)]"));

    }


    @Value("${system.debug:false}")
    private boolean isDebug;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isDebug){
            User user = new User();
            user.setUid(1l);
            user.setuName("Debug用户");
            user.setLoginIp("127.127.127.127");
            request.getSession().setAttribute("user",user);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String uri = request.getRequestURI();
        //是否需要过滤
        boolean notNeed = notNeedFilter(uri);
        //不需要过滤直接传给下一个过滤器
        if (notNeed) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //如果已经登陆
        if (checkSession(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String requestType = request.getHeader("X-Requested-With");
        response.setContentType("application/json;charset=UTF-8");
        //判断是否是ajax请求
        if(requestType!=null && "XMLHttpRequest".equals(requestType)){
            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_Login).toString());
        }else{
            //重定向到登录页(需要在static文件夹下建立此html文件)
            response.sendRedirect(request.getContextPath()+no_login);
//            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_Login).toString());
        }
        return;
    }

    private boolean notNeedFilter(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
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
    public void destroy() {

    }
}