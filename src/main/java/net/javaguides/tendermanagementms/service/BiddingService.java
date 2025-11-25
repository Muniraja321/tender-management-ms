package net.javaguides.tendermanagementms.service;

import net.javaguides.tendermanagementms.model.BiddingModel;
import net.javaguides.tendermanagementms.repository.BiddingRepository;
import org.springframework.http.ResponseEntity;

public class BiddingService {

    private BiddingRepository biddingRepository;

    private UserService userService;

//to add the Bidding using BiddingModel object

//created->201

//badRequest->400

    public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
        return null;
    }

//to get the bidding details which are greater than the given bidAmount

//ok()->200

//badRequest()->400
    public ResponseEntity<Object> getBidding(double bidAmount) {
        return null;
    }

//to update the bidding status

//ok->200

//badRequest->400
    public ResponseEntity<Object> updateBidding(int id, BiddingModel model) {
        return null;
    }

 //to delete the Bidding by using id

//approver and only the creater of that particular Bidding can delete

//noContent->204

//badRequest->400

//forbidden->403


    public ResponseEntity<Object> deleteBidding(int id) {

        return null;
    }
}
