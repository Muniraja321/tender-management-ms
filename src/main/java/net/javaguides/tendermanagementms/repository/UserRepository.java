package net.javaguides.tendermanagementms.repository;

import net.javaguides.tendermanagementms.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findByEmail(String email);
}
