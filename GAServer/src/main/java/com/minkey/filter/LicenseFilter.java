package com.minkey.filter;

import com.minkey.dto.JSONMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 证书过滤
 */
@WebFilter(filterName = "licenseFilter",urlPatterns = {"/*"})
public class LicenseFilter implements Filter {

    String NO_license = "您还没有注册license";

    //Minkey license白名单url没补全
    final String[] includeUrls = new String[]{

            "/license.html",
            "/license/upFile",
            "/license/getCode"
    };

    @Value("${system.debug}")
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
        boolean needFilter = isNeedFilter(uri);

        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //如果有证书
        if (checkLicense()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String requestType = request.getHeader("X-Requested-With");
        //判断是否是ajax请求
        if(requestType!=null && "XMLHttpRequest".equals(requestType)){
            response.getWriter().write(JSONMessage.createFalied(this.NO_license).toString());
        }else{
            //重定向到登录页(需要在static文件夹下建立此html文件)
            response.sendRedirect(request.getContextPath()+"/license.html");
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

    private boolean checkLicense() {

        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}