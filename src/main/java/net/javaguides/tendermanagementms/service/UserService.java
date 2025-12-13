package net.javaguides.tendermanagementms.service;

import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.RoleRepository;
import net.javaguides.tendermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    //get user by email

    public UserModel getUserByEmail(String email){

        return userRepository.findByEmail(email);
    }
}
