package net.javaguides.tendermanagementms.service;

import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("user not found with this email" + email);
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole().getRolename());

        return buildUserForAuthentication(user,authorities);
    }

    //This must return Spring Security’s User object:
    private UserDetails buildUserForAuthentication(UserModel user, List<GrantedAuthority> authorities) {

        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );

    }

//    This converts your RoleModel → SimpleGrantedAuthority
//
//    Example:
//
//            "BIDDER" → "ROLE_BIDDER"
//    Spring Security needs role in this format.

    private List<GrantedAuthority> buildUserAuthority(String userRole) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole));
        return authorities;

    }
}
