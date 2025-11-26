package net.javaguides.tendermanagementms.configuration;

import net.javaguides.tendermanagementms.model.RoleModel;
import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.RoleRepository;
import net.javaguides.tendermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) throws InterruptedException {

       RoleModel bidderRole =  roleRepository.save(new RoleModel("BIDDER"));

        RoleModel approvalRole = roleRepository.save(new RoleModel( "APPROVER"));

        userRepository.save(new UserModel(
                1,
                "bidder1",
                "companyOne",
                passwordEncoder.encode("bidder123$"),
                "bidderemail@gmail.com",
                bidderRole));

        userRepository.save(new UserModel(
                2,
                "bidder2",
                "companyTwo",
                passwordEncoder.encode("bidder789$"),
                "bidderemail2@gmail.com",
                bidderRole
        ));

        userRepository.save(new UserModel(
                3,
                "approver",
                "defaultCompany",
                passwordEncoder.encode("approver1235"),
                "approveremail@gmail.com",
                approvalRole
        ));



    }
}
