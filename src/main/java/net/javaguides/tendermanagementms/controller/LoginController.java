package net.javaguides.tendermanagementms.controller;

import net.javaguides.tendermanagementms.dto.LoginDTO;
import net.javaguides.tendermanagementms.security.JWTUtil;
import net.javaguides.tendermanagementms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    LoginService loginService;

    @Autowired
    private JWTUtil jwtTokenUtil;

    @PostMapping("/login")
    public Object authenticateUser(LoginDTO authenticationRequest) throws Exception{
        return null;
    }

}
