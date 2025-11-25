package net.javaguides.tendermanagementms.configuration;

import net.javaguides.tendermanagementms.model.RoleModel;
import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.RoleRepository;
import net.javaguides.tendermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) throws InterruptedException {

        roleRepository.save(new RoleModel("BIDDER"));

        roleRepository.save(new RoleModel( "APPROVER"));

        userRepository.save(new UserModel(
                1,
                "bidder1",
                "companyOne",
                "bidder123$",
                "bidderemail@gmail.com",
                new RoleModel(1)));

        userRepository.save(new UserModel(
                2,
                "bidder2",
                "companyTwo",
                "bidder789$",
                "bidderemail2@gmail.com",
                new RoleModel(1)
        ));

        userRepository.save(new UserModel(
                3,
                "approver",
                "defaultCompany",
                "approver1235",
                "approveremail@gmail.com",
                new RoleModel(2)
        ));



    }
}
