package net.javaguides.tendermanagementms.repository;

import net.javaguides.tendermanagementms.model.BiddingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends JpaRepository<BiddingModel,Integer> {
    List<BiddingModel> findByBidAmountGreaterThan(double bidAmount);

}
