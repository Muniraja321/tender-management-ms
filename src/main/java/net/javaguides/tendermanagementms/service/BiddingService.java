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
import java.util.Optional;

@Service
public class BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Autowired
    private UserService userService;

    public ResponseEntity<Object> postBidding(BiddingModel biddingModel){
       String email =  getCurrentEmail();
       UserModel user = userService.getUserByEmail(email);

       if(user == null){
           return ResponseEntity.status(400).body("Not found in DB");
       }

       biddingModel.setBiddingId(user.getId());
       biddingModel.setDate0fBidding(getCurrentDate());
       biddingRepository.save(biddingModel);
        return ResponseEntity.status(201).body(biddingModel);
    }

    public ResponseEntity<Object> getBidding(Double bidAmount){

       List<BiddingModel> biddingModel = biddingRepository.findByBidAmountGreaterThan(bidAmount);

       if(biddingModel.isEmpty()){
           return ResponseEntity.status(400).body("Not found in DB");
       }
        return ResponseEntity.status(200).body(biddingModel);
    }

    public ResponseEntity<Object> updateBidding(int id, BiddingModel biddingModel){

        String email = getCurrentEmail();
        UserModel user = userService.getUserByEmail(email);

        if(user == null){
            return ResponseEntity.status(400).body("Not Found in DB");
        }

        Optional<BiddingModel> bidModel = biddingRepository.findById(id);

        if(!bidModel.isPresent()){
            return ResponseEntity.status(400).body("Not found in DB");
        }

        BiddingModel existing = bidModel.get();
        existing.setStatus(biddingModel.getStatus());
        biddingRepository.save(existing);
        return ResponseEntity.status(200).body(existing);
    }

    public ResponseEntity<Object> deleteBidding(int id){

        try{
            String email = getCurrentEmail();
            UserModel user = userService.getUserByEmail(email);
            if(user == null){
                return ResponseEntity.status(400).body("Not Found in DB");
            }

            Optional<BiddingModel> bidModel = biddingRepository.findById(id);
            if(!bidModel.isPresent()){
                return ResponseEntity.status(400).body("Not Found in DB");
            }

            if(user.getId() == bidModel.get().getBidderId() || user.getRole().getRolename().equalsIgnoreCase("APPROVER")){
                biddingRepository.deleteById(id);
                return ResponseEntity.status(204).body("Deleted Successfully");
            }
            return ResponseEntity.status(403).body("You dont have permission");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Not Found");
        }
    }

    private String getCurrentEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    private String getCurrentDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

}
