package net.javaguides.tendermanagementms.controller;

import net.javaguides.tendermanagementms.dto.LoginDTO;
import net.javaguides.tendermanagementms.security.JWTUtil;
import net.javaguides.tendermanagementms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    LoginService loginService;

    @Autowired
    private JWTUtil jwtTokenUtil;

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginDTO authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            UserDetails userDetails = loginService.loadUserByUsername(authenticationRequest.getEmail());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            Map<String,Object> response = new HashMap<>();
            response.put("jwt",jwt);
            response.put("status",HttpStatus.OK.value());
            return ResponseEntity.ok(response);


        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.BAD_REQUEST);
        }

    }

}
