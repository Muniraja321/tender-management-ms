package net.javaguides.tendermanagementms.controller;

import net.javaguides.tendermanagementms.model.BiddingModel;
import net.javaguides.tendermanagementms.service.BiddingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bidding")
@RestController
public class BiddingController {
    private BiddingService biddingService;


    @PostMapping("/add")
    public ResponseEntity<Object> postBidding(@RequestBody BiddingModel biddingModel) {
        return biddingService.postBidding(biddingModel);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getBidding(double bidAmount) {
        return null;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBidding(int id, BiddingModel biddingModel) {
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBidding(int id) {
       return null;
   }

}