package net.javaguides.tendermanagementms.repository;

import net.javaguides.tendermanagementms.model.BiddingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingRepository extends JpaRepository<BiddingModel,Integer> {
}
