package patterns.behavioral.observer.auction_system;

interface BidObserver{
    void update(String itemName, int currentBid, String highestBidder);
}

class Bidder implements BidObserver {

    private String name;
    private final int maxAmount;

    public Bidder(String name, int maxAmount) {
        this.name = name;
        this.maxAmount = maxAmount;
    }

    public void bid(int bidAmount, AuctionItem item){
        if (bidAmount <= 0) {
            throw new IllegalArgumentException("Bid amount must be greater than zero.");
        }else if(bidAmount> maxAmount) {
            System.out.println(name + " Cannot place bid of " + bidAmount + " as it exceeds maximum allowed amount of " + maxAmount);
        }else{
            item.placeBid(bidAmount, name);
        }
    }

    @Override
    public void update(String itemName, int currentBid, String highestBidder) {
        if(name.equalsIgnoreCase(highestBidder)){
            System.out.println(name + " you are the highest bidder on " + itemName + " with a bid of: " + currentBid);
        } else {
            System.out.println(name + " has been outbid on " + itemName + " by the highest bidder: " + highestBidder + "for amount: " + currentBid);
        }


    }

    public String getName() {
        return name;
    }
}