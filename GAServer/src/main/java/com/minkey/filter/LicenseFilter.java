package com.minkey.filter;

import com.minkey.contants.ErrorCodeEnum;
import com.minkey.dto.JSONMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 证书过滤，第一个执行
 */
@WebFilter(filterName = "licenseFilter",urlPatterns = {"/*"})
public class LicenseFilter implements Filter {

    //license白名单url
    final String[] includeUrls = new String[]{
            "/license.html",
            //Minkey 生成证书到时候要删掉
            "/license/licenseExport",

            "/license/up",
            "/license/keyExport",
            "/license/key"
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
        response.setContentType("application/json;charset=UTF-8");
        //判断是否是ajax请求
        if(requestType!=null && "XMLHttpRequest".equals(requestType)){
            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_license).toString());
        }else{
            //重定向到登录页(需要在static文件夹下建立此html文件)
//            response.sendRedirect(request.getContextPath()+"/license.html");
            response.getWriter().write(JSONMessage.createFalied(ErrorCodeEnum.No_license).toString());
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
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}