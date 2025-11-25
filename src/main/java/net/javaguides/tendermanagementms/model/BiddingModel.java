package net.javaguides.tendermanagementms.model;

import javax.persistence.*;

@Entity
public class BiddingModel {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(unique = true)

    private int biddingId;

    @Override
    public String toString() {
        return "BiddingModel{" +
                "id=" + id +
                ", biddingId=" + biddingId +
                ", projectName='" + projectName + '\'' +
                ", bidAmount=" + bidAmount +
                ", yearsToComplete=" + yearsToComplete +
                ", date0fBidding='" + date0fBidding + '\'' +
                ", status='" + status + '\'' +
                ", bidderId=" + bidderId +
                '}';
    }

    private final String projectName="Metro Phase V 2024";

    private Double bidAmount;

    public BiddingModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(int biddingId) {
        this.biddingId = biddingId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Double getYearsToComplete() {
        return yearsToComplete;
    }

    public void setYearsToComplete(Double yearsToComplete) {
        this.yearsToComplete = yearsToComplete;
    }

    public String getDate0fBidding() {
        return date0fBidding;
    }

    public void setDate0fBidding(String date0fBidding) {
        this.date0fBidding = date0fBidding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public BiddingModel(int id, int biddingId, Double bidAmount, Double yearsToComplete, String date0fBidding, String status, int bidderId) {
        this.id = id;
        this.biddingId = biddingId;
        this.bidAmount = bidAmount;
        this.yearsToComplete = yearsToComplete;
        this.date0fBidding = date0fBidding;
        this.status = status;
        this.bidderId = bidderId;
    }

    private Double yearsToComplete;

    private String date0fBidding;

    private String status = "pending";

    private int bidderId;
}
