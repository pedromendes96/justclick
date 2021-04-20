package org.justclick.project.middleware;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CookieMiddleware extends OncePerRequestFilter {
    private String cookieName;

    @Autowired
    public CookieMiddleware(Environment env) {
        this.cookieName = env.getProperty("cookie.name");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(response != null){
            Cookie[] cookies = request.getCookies();
            boolean hasCookie = Arrays.stream(cookies).anyMatch(x -> x.getName().equals(cookieName));
            if(!hasCookie){
                UUID uuid = UUID.randomUUID();
                Cookie cookie = new Cookie(this.cookieName, uuid.toString());
                response.addCookie(cookie);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
