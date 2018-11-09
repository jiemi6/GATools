package com.minkey.filter;

import com.minkey.contants.ErrorCodeEnum;
import com.minkey.dto.JSONMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 证书过滤，第一个执行
 */
@WebFilter(filterName = "licenseFilter",urlPatterns = {"/*"})
public class LicenseFilter implements Filter {

    public static final String no_license = "/license.html";

    protected List<Pattern> patterns = new ArrayList<Pattern>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile(no_license));

        patterns.add(Pattern.compile("/license/up"));
        patterns.add(Pattern.compile("/license/keyExport"));
        patterns.add(Pattern.compile("/license/key"));

        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)||(\\.tff)]"));
    }

    @Value("${system.debug:false}")
    private boolean isDebug;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isDebug){
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

        //如果有证书
        if (checkLicense()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String requestType = request.getHeader("X-Requested-With");
        response.setContentType("application/json;charset=UTF-8");
        //判断是否是ajax请求
        if(requestType!=null && "XMLHttpRequest".equals(requestType)){
            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_license).toString());
        }else{
            //重定向到登录页(需要在static文件夹下建立此html文件)
            response.sendRedirect(request.getContextPath()+no_license);
//            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_license).toString());
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

    private boolean checkLicense() {
//        JSONObject license = SpringUtils.getBean(LicenseController.class).getLicenseData();


        return true;
    }


    @Override
    public void destroy() {

    }
}