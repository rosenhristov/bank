package com.rosenhristov.bank.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[PreHandle][{}][{}] {}", request, request.getMethod(), getRequestUriWithParams(request));
        return true;
    }

    private String getRequestUriWithParams(HttpServletRequest request) {
        StringBuffer sbuffer = new StringBuffer(request.getRequestURI());
        Enumeration<?> params = request.getParameterNames();
        if (params == null) {
            sbuffer.append("?");
        }
        while (params.hasMoreElements()) {
            if(sbuffer.charAt(sbuffer.length() - 1) != '?') {
                sbuffer.append("&");
            }
            String param = (String) params.nextElement();
            sbuffer.append(param + "=");
            if (param.contains("password") || param.contains("pass")
                                        || param.contains("pwd")) {
                sbuffer.append("*****");
            } else {
                sbuffer.append(request.getParameter(param));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null)
                ? getRemoteAddr(request)
                : ip;
        if (StringUtils.isNotEmpty(ipAddr)) {
            sbuffer.append("&_psip=" + ipAddr);
        }
        return sbuffer.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isNotEmpty(ipFromHeader)) {
            log.debug("ip from proxy X-FORWARDED-FOR : {}", ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {

        log.info("[postHandle][{}]", request);
    }
}
