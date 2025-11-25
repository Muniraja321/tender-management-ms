package net.javaguides.tendermanagementms.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.javaguides.tendermanagementms.service.LoginService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PrivateKey;

public class AuthenticationFilter extends OncePerRequestFilter {

    private JWTUtil jwtTokenUtil;
    private LoginService loginService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
