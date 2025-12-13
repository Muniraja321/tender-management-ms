package net.javaguides.tendermanagementms.controller;

import net.javaguides.tendermanagementms.model.BiddingModel;
import net.javaguides.tendermanagementms.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bidding")
public class BiddingController {

    @Autowired
    private BiddingService biddingService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_BIDDER')")
    public ResponseEntity<Object> postBidding(@RequestBody BiddingModel biddingModel) {
        return biddingService.postBidding(biddingModel);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE_BIDDER','ROLE_APPROVER')")
    public ResponseEntity<Object> getBidding(@RequestParam Double bidAmount) {

        return biddingService.getBidding(bidAmount);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_APPROVER')")
    public ResponseEntity<Object> updateBidding(@PathVariable("id") int id, @RequestBody BiddingModel biddingModel) {
        return biddingService.updateBidding(id,biddingModel);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_APPROVER','ROLE_BIDDER')")
    public ResponseEntity<Object> deleteBidding(@PathVariable("id") int id) {
        return biddingService.deleteBidding(id);
   }

}