package patterns.behavioral.observer.auction_system;

import java.util.ArrayList;

interface Observable{
    void addObserver(BidObserver observer);
    void removeObserver(BidObserver observer);
    void notifyObservers();
}

//class ItemDTO {
//    private final String name;
//    private int currentBid;
//    private String highestBidder;
//
//    public ItemDTO(String name, int currentBid, String highestBidder) {
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be null or empty");
//        }
//        if( currentBid < 0) {
//            throw new IllegalArgumentException("Current bid cannot be negative");
//        }
//        if (highestBidder == null || highestBidder.isEmpty()) {
//            throw new IllegalArgumentException("Highest bidder cannot be null or empty");
//        }
//        this.name = name;
//        this.currentBid = currentBid;
//        this.highestBidder = highestBidder;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCurrentBid() {
//        return currentBid;
//    }
//
//    public String getHighestBidder() {
//        return highestBidder;
//    }
//
//}

class AuctionItem implements Observable{

    private final ArrayList<BidObserver> observers;
    private final String name;
    private int currentBid;
    private String highestBidder;

    public AuctionItem(String name){
        this.name = name;
        this.observers = new ArrayList<BidObserver>();
    }

    public String getName() {
        return name;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void placeBid(int bidAmount, String bidderName) {
        if (bidAmount <= currentBid) {
            throw new IllegalArgumentException("Bid amount must be higher than the current bid.");
        }
        this.currentBid = bidAmount;
        this.highestBidder = bidderName;
        notifyObservers();
    }

    @Override
    public void addObserver(BidObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BidObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(name, currentBid, highestBidder));
    }
}