package net.javaguides.tendermanagementms.service;

import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       UserModel user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("usernot found with this email");
        }
        return new User(
                user.getEmail(),user.getPassword(),List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRolename()))
        );
    }
}
