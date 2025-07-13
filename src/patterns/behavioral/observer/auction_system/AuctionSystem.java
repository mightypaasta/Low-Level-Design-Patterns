package patterns.behavioral.observer.auction_system;

public class AuctionSystem {
    public void solve() {
        // This is a placeholder for the solution to Problem 2.
        // The actual implementation would involve creating instances of
        // AuctionItem and Bidder, and demonstrating the observer pattern in action.

        System.out.println("Problem 2: Observer Pattern Example in Auction System");

        AuctionItem item = new AuctionItem("Antique Vase");
        Bidder bidder1 = new Bidder("Alice", 100);
        Bidder bidder2 = new Bidder("Bob", 150);

        item.addObserver(bidder1);
        item.addObserver(bidder2);

        try {
            bidder1.bid(120, item); // Alice bids 120
            bidder2.bid(130, item); // Bob bids 130
            bidder1.bid(140, item); // Alice bids 140
            bidder2.bid(160, item); // Bob bids 160
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Final highest bid on " + item.getName() + ": " + item.getCurrentBid() + " by " + item.getHighestBidder());
    }
}
