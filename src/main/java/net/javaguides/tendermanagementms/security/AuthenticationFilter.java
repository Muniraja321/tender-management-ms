package net.javaguides.tendermanagementms.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.javaguides.tendermanagementms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PrivateKey;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
           jwt =  authorizationHeader.substring(7);
           username = jwtTokenUtil.getUsernameFromToken(jwt);
        }


        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails =  loginService.loadUserByUsername(username);
           if(jwtTokenUtil.validateToken(jwt,userDetails)){
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
        }
        filterChain.doFilter(request,response);



    }
}
