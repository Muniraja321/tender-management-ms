package net.javaguides.tendermanagementms.controller;

import net.javaguides.tendermanagementms.model.BiddingModel;
import net.javaguides.tendermanagementms.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bidding")
@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;


    @PostMapping("/add")
    public ResponseEntity<Object> postBidding(@RequestBody BiddingModel biddingModel) {
        return biddingService.postBidding(biddingModel);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getBidding(@RequestParam Double bidAmount) {

        return biddingService.getBidding(bidAmount);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBidding(@PathVariable int id, @RequestBody BiddingModel biddingModel) {
        return biddingService.updateBidding(id,biddingModel);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBidding(@PathVariable("id") int id) {
        System.out.println("==== DELETE API HIT ===== id = " + id);

        return biddingService.deleteBidding(id);
   }

}