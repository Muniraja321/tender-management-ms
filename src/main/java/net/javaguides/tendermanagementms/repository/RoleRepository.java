package net.javaguides.tendermanagementms.repository;

import net.javaguides.tendermanagementms.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Integer> {
}
