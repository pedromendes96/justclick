package org.justclick.project.middleware;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LogRequestMiddleware extends OncePerRequestFilter {

    private Logger log = LogManager.getLogger(LogRequestMiddleware.class);
    
    private String getRequestInfo(HttpServletRequest request){
        JSONObject context = new JSONObject();
        context.put("headers", this.getHeaders(request));
        context.put("params", this.getParams(request));
        context.put("cookies", this.getCookies(request));
        return context.toString();
    }

    private JSONObject getCookies(HttpServletRequest request) {
        JSONObject context = new JSONObject();
        for (Cookie cookie : request.getCookies()) {
            context.put(cookie.getName(), cookie.getValue());
        }
        return context;
    }

    private JSONObject getParams(HttpServletRequest request) {
        JSONObject context = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            context.put(parameterName, request.getParameter(parameterName));
        }
        return context;
    }

    private JSONObject getHeaders(HttpServletRequest request) {
        JSONObject context = new JSONObject();
        Enumeration<String> headersNames = request.getHeaderNames();
        while (headersNames.hasMoreElements()) {
            String headerName = headersNames.nextElement();
            context.put(headerName, request.getHeader(headerName));
        }
        return context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request != null && response != null){
            this.log.debug(this.getRequestInfo(request));
        }
        filterChain.doFilter(request, response);
    }
    
}
