package net.javaguides.tendermanagementms.service;

import net.javaguides.tendermanagementms.model.BiddingModel;
import net.javaguides.tendermanagementms.model.UserModel;
import net.javaguides.tendermanagementms.repository.BiddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Autowired
    private UserService userService;

//to add the Bidding using BiddingModel object

//created->201

//badRequest->400


    public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
        try{
            String email = getCurrentEmail();
            UserModel user = userService.getUserByEmail(email);

            if(!"BIDDER".equalsIgnoreCase(user.getRole().getRolename())){
                return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
            }
            biddingModel.setBiddingId(user.getId());
            biddingModel.setDate0fBidding(getCurrentDate());

            biddingRepository.save(biddingModel);

            return new ResponseEntity<>(biddingModel,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
        }
    }

//to get the bidding details which are greater than the given bidAmount

//ok()->200

//badRequest()->400
    public ResponseEntity<Object> getBidding(Double bidAmount) {
       List<BiddingModel> results =  biddingRepository.findByBidAmountGreaterThan(bidAmount);
       if(results.isEmpty()){
           return new ResponseEntity<>("No data available", HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(results,HttpStatus.OK);
    }

//to update the bidding status

//ok->200

//badRequest->400
    public ResponseEntity<Object> updateBidding(int id, BiddingModel model) {
        try{
            BiddingModel biddingModel = biddingRepository.findById(id).orElse(null);
            if(biddingModel == null){
                return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
            }

            String email = getCurrentEmail();
            UserModel user = userService.getUserByEmail(email);
            if(!"APPROVER".equals(user.getRole().getRolename())){
                return new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN);
            }
            biddingModel.setStatus(model.getStatus());
            biddingRepository.save(biddingModel);
            return new ResponseEntity<>(biddingModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("BAD REQUEST",HttpStatus.BAD_REQUEST);
        }
    }

 //to delete the Bidding by using id

//approver and only the creater of that particular Bidding can delete

//noContent->204

//badRequest->400

//forbidden->403


    public ResponseEntity<Object> deleteBidding(int id) {
        try{
            BiddingModel biddingModel  = biddingRepository.findById(id).orElse(null);
            System.out.println("FOUND MODEL = " + biddingModel);
            if(biddingModel == null){
                return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
            }
            String email = getCurrentEmail();
            System.out.println("CURRENT EMAIL = " + email);
            UserModel user = userService.getUserByEmail(email);
            System.out.println("USER FOUND = " + user);
            System.out.println("USER ROLE = " + user.getRole().getRolename());
            System.out.println("BIDDER ID = " + biddingModel.getBidderId());
            System.out.println("USER ID = " + user.getId());
            if("APPROVER".equals(user.getRole().getRolename()) || biddingModel.getBidderId() == user.getId()){
                biddingRepository.delete(biddingModel);
                return ResponseEntity.noContent().build();
            }else{
                return new ResponseEntity<>("You dont have permission to delete",HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
        }
    }

    private String getCurrentEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    private String getCurrentDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }


}
