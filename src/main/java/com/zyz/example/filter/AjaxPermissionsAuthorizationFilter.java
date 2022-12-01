package com.zyz.example.filter;

import com.alibaba.fastjson.JSON;
import com.zyz.example.common.ApiResponse;
import com.zyz.example.constants.StateCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {
    private final static Logger log = LoggerFactory.getLogger(AjaxPermissionsAuthorizationFilter.class);

    /**
     * 登录拒绝后的处理
     * @param request
     * @param response
     * @return true(表示继续执行后续拦截器) false(由当前方法处理结束)
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        log.error("没有访问权限...{}", ((HttpServletRequest) request).getRequestURL());

        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(JSON.toJSONString(ApiResponse.error(StateCode.NO_AUTH)));
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }
//
//    @Bean
//    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
}